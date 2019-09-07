package com.amk.morris

import android.Manifest
import android.animation.ObjectAnimator
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.squareup.picasso.Picasso

class SignActivity : AppCompatActivity() {

    private val galleryReqCode: Int = 100
    private val permissionCode: Int = 101
    private var profileImage: ImageView? = null
    private var email_txt: EditText? = null
    private var pass_txt: EditText? = null
    private var name_txt: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        email_txt = findViewById(R.id.useremail_txt)
        pass_txt = findViewById(R.id.userpass_txt)
        pass_txt = findViewById(R.id.username_txt)
        profileImage = findViewById(R.id.profile_image)
        val background_image = findViewById<ImageView>(R.id.imageView4)
        ObjectAnimator.ofFloat(background_image, View.ROTATION, 0f, 360f).setDuration(30000).start()
    }

    fun onSubmit(view: View) {
        val email = email_txt?.text.toString()
        val name = name_txt?.text.toString()
        val pass = pass_txt?.text.toString()
//        if (email.isEmpty() || name.isEmpty() || pass.isEmpty()) {
//            val alert = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
//            alert.titleText = "ارور!"
//            alert.contentText = "همه فیلدها را پر کنید"
//            alert.show()
//        }
        //TODO: Send info's to the server and get callback
        val toMain = Intent(this, ProfileActivity::class.java)
        startActivity(toMain)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            Log.i("TAG", data?.data.toString())
            Picasso.get().load(data?.data).fit().into(profileImage)
        }
    }

    fun ongologinTxt(view: View) {
        val gotologin = Intent(this, Login::class.java)
        startActivity(gotologin)
        finish()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            permissionCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, galleryReqCode)
    }

    fun onProfileImage(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                //permission denied
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                //show popup to request runtime permission
                requestPermissions(permissions, permissionCode)
            } else {
                //permission already granted
                pickImageFromGallery()
            }
        } else {
            //system OS is < Marshmallow
            pickImageFromGallery()
        }
    }

}
