package com.amk.morris

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog


class Login : AppCompatActivity() {

    lateinit var email_txt: EditText
    lateinit var pass_txt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email_txt = findViewById(R.id.email_txt)
        pass_txt = findViewById(R.id.pass_txt)
        val background_image = findViewById<ImageView>(R.id.back_image)
        ObjectAnimator.ofFloat(background_image, View.ROTATION, 0f, 360f).setDuration(30000).start()
    }


    fun onForgotTxt(view: View) {
        val toMain = Intent(this, SendToEmail::class.java)
        startActivity(toMain)
        finish()
    }

    fun onSubmit(view: View) {
        val email = email_txt.text.toString()
        val passTxt = pass_txt.text.toString()
        if (passTxt.isEmpty() || email.isEmpty()) {
            run {
                val alert = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                alert.titleText = "مشکل!"
                alert.contentText = "نام کاربری و رمز عبور خود را وارد کنید"
                alert.setConfirmText("باشه")
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
