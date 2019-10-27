package com.amk.morris

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.amk.morris.API.APIClient
import com.amk.morris.API.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SendToEmail : AppCompatActivity() {


    private lateinit var email_txt: EditText
    val alert = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_to_email)
        email_txt = findViewById(R.id.forgot_email_txt)
        val backgroundImage = findViewById<ImageView>(R.id.back_image)
        ObjectAnimator.ofFloat(backgroundImage, View.ROTATION, 0f, 360f).setDuration(60000).start()
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
            val email_address = view.findViewById<EditText>(R.id.forgot_email_txt).text.toString()
            val apiInterface = APIClient.getRetrofit().create(APIInterface::class.java)
            val call = apiInterface.forgetPwd(email_address) as Call<String>
            call.enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    alert.titleText = "ارور!"
                    alert.contentText = "ایمیل معتبر نیست!"
                    alert.confirmText = "باشه"
                    alert.show()
                    alert.setConfirmClickListener {
                        alert.dismiss()
                    }
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    alert.titleText = "ارور!"
                    alert.contentText = "لینک تغییر پسورذ به ایمیل تون با موفقیت ارسال شد!"
                    alert.confirmText = "باشه"
                    alert.show()
                    alert.setConfirmClickListener {
                        alert.dismiss()
                    }
                }

            })
        }
    }

    fun onGotoLogin(view: View) {
        val gotologin = Intent(this, Login::class.java)
        startActivity(gotologin)
        finish()
    }

}
