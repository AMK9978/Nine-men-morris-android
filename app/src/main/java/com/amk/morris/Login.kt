package com.amk.morris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var background_image = findViewById<ImageView>(R.id.imageView4)

    }


    fun onForgotTxt(view: View) {
        val toMain = Intent(this, SendToEmail::class.java)
        startActivity(toMain)
    }

    fun onSubmit(view: View) {
        var email = view.findViewById<EditText>(R.id.email_txt).text.toString()
        var pass_txt = view.findViewById<EditText>(R.id.pass_txt).text.toString()
        //TODO: Send info's to server and get callback

        val toMain = Intent(this, MainActivity::class.java)
        startActivity(toMain)
    }

    fun onSignTxt(view: View) {
        val toSign = Intent(this, SignActivity::class.java)
        startActivity(toSign)
    }
}
