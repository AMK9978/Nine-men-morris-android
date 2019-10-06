package com.amk.morris

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.amk.morris.API.APIClient
import com.amk.morris.API.APIInterface
import com.amk.morris.Model.Person
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("UNUSED_PARAMETER")
class Login : AppCompatActivity() {

    private lateinit var emailTxt: EditText
    private lateinit var passTxt: EditText

    val alert = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)

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
            val toMain = Intent(this, ProfileActivity::class.java)
            val apiInterface = APIClient.getRetrofit().create(APIInterface::class.java)
            val call = apiInterface.login(email, passTxt) as Call<Person>
            call.enqueue(object : Callback<Person> {
                override fun onFailure(call: Call<Person>, t: Throwable) {
                    Log.i("TAG", t.message)
                    alert.titleText = "مشکل!"
                    alert.contentText = "مشکلی در وارد کردن نام کاربری و رمز عبور هست!"
                    alert.confirmText = "باشه"
                    alert.show()
                    alert.setConfirmClickListener {
                        alert.dismiss()
                    }
                    emailTxt.text.clear()
                    pass_txt.text.clear()
                    return
                }

                override fun onResponse(call: Call<Person>, response: Response<Person>) {
                    if (response.isSuccessful){
                        val person = response.body()
                        toMain.putExtra("person", person)
                        startActivity(toMain)
                    }else{
                        emailTxt.text.clear()
                        pass_txt.text.clear()
                        return
                    }
                }
            })
        }
    }

    fun onSignTxt(view: View) {
        val toSign = Intent(this, SignActivity::class.java)
        startActivity(toSign)
        finish()
    }

}
