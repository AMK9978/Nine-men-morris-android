package com.amk.morris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class SendToEmail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_to_email)
    }

    fun onSendEmail(view: View) {
        val email_address = view.findViewById<EditText>(R.id.forgot_email_txt)
        //TODO: Send email address to the server for sending password and get callback

    }

    fun onGotoLogin(view: View) {
        val gotologin = Intent(this, Login::class.java)
        startActivity(gotologin)
    }

}
