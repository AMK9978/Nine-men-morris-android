package com.amk.morris

import android.Manifest
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.amk.morris.API.APIClient
import com.amk.morris.API.APIInterface
import com.amk.morris.Model.Person
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream


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
        name_txt = findViewById(R.id.username_txt)
        profileImage = findViewById(R.id.profile_image)
        val background_image = findViewById<ImageView>(R.id.imageView4)
        ObjectAnimator.ofFloat(background_image, View.ROTATION, 0f, 360f).setDuration(60000).start()
    }

    fun onSubmit(view: View) {
        val email = email_txt?.text.toString()
        val name = name_txt?.text.toString()
        val pass = pass_txt?.text.toString()
        if (email.isEmpty() || name.isEmpty() || pass.isEmpty()) {
            val alert = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            alert.titleText = "مشکل!"
            alert.contentText = "همه فیلدها را پر کنید"
            alert.show()
            alert.setConfirmClickListener {
                alert.dismiss()
            }
        } else {
            val toMain = Intent(this, ProfileActivity::class.java)
            val apiInterface = APIClient.getRetrofit().create(APIInterface::class.java)
            Log.i("TAG", "$name , $email , $pass")
            val call = apiInterface.signUp(name, email, pass) as Call<Person>
            call.enqueue(object : Callback<Person> {
                override fun onFailure(call: Call<Person>, t: Throwable) {
                    Log.i("TAGGG", t.toString())
                    name_txt!!.text.clear()
                    email_txt!!.text.clear()
                    pass_txt!!.text.clear()
                    return
                }

                override fun onResponse(call: Call<Person>, response: Response<Person>) {
                    if (response.isSuccessful){
                        saveImageToInternalStorage(profileImage!!.drawable)
                        val editor = Util.sharedPreferences.edit()
                        editor.putString("name", name)
                        editor.putString("email", email)
                        editor.apply()
                        val selfName = getSharedPreferences("pref", Context.MODE_PRIVATE)!!.getString("name", "haji") as String
                        Log.i("TAG", "NAME: $selfName, $name")
                        val person = response.body()
                        toMain.putExtra("person", person)
                        startActivity(toMain)
                        finish()
                    }else{
                        Log.i("TAG", response.message())
                        name_txt?.text?.clear()
                        email_txt?.text?.clear()
                        pass_txt?.text?.clear()
                        return
                    }
                }
            })
        }
    }

    private fun saveImageToInternalStorage(drawable: Drawable) {
        val bitmap = (drawable as BitmapDrawable).bitmap
        val mainFile = "Morris"
        val f = File(Environment.getExternalStorageDirectory(), mainFile)
        if (!f.exists()) {
            f.mkdirs()
        }
        val mypath = "profileImage.jpg"
        val mypathfile = File(f.absolutePath + "/" + mypath)
        val fos = FileOutputStream(mypathfile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            Log.i("TAG", data?.data.toString())
            Picasso.get().load(data?.data).fit().placeholder(R.drawable.ic_person_outline_black_24dp).into(profileImage)
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
