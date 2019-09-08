package com.amk.morris

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog

class SendToEmail : AppCompatActivity() {


    private lateinit var email_txt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_to_email)
        email_txt = findViewById(R.id.forgot_email_txt)
        val background_image = findViewById<ImageView>(R.id.back_image)
        ObjectAnimator.ofFloat(background_image, View.ROTATION, 0f, 360f).setDuration(60000).start()
    }

    fun onSendEmail(view: View) {
        val email = email_txt.text.toString()
        if (email.isEmpty()){
            val alert = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            alert.titleText = "ارور!"
            alert.contentText = "ایمیلت  رو وارد کن"
            alert.setConfirmText("باشه")
            alert.show()
            alert.setConfirmClickListener {
                alert.dismiss()
            }
        }else {
            val email_address = view.findViewById<EditText>(R.id.forgot_email_txt)
            //TODO: Send email address to the server for sending password and get callback
        }
    }

    fun onGotoLogin(view: View) {
        val gotologin = Intent(this, Login::class.java)
        startActivity(gotologin)
        finish()
    }

}
