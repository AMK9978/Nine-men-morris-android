package com.amk.morris

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog


@Suppress("UNUSED_PARAMETER")
class Login : AppCompatActivity() {

    private lateinit var emailTxt: EditText
    private lateinit var passTxt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        emailTxt = findViewById(R.id.email_txt)
        passTxt = findViewById(R.id.pass_txt)
        val backgroundImage = findViewById<ImageView>(R.id.back_image)
        ObjectAnimator.ofFloat(backgroundImage, View.ROTATION, 0f, 360f).setDuration(30000).start()
    }


    fun onForgotTxt(view: View) {
        val toMain = Intent(this, SendToEmail::class.java)
        startActivity(toMain)
        finish()
    }

    fun onSubmit(view: View) {
        val email = emailTxt.text.toString()
        val passTxt = passTxt.text.toString()
        if (passTxt.isEmpty() || email.isEmpty()) {
            run {
                val alert = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                alert.titleText = "مشکل!"
                alert.contentText = "نام کاربری و رمز عبور خود را وارد کنید"
                alert.confirmText = "باشه"
                alert.show()
                alert.setConfirmClickListener {
                    alert.dismiss()
                }
            }
        } else {
            //TODO: Send info's to server and get callback
            val toMain = Intent(this, ProfileActivity::class.java)
            startActivity(toMain)
        }
    }

    fun onSignTxt(view: View) {
        val toSign = Intent(this, SignActivity::class.java)
        startActivity(toSign)
        finish()
    }

}
