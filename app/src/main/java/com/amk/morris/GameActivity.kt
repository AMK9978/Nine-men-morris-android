package com.amk.morris

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    val self_name: TextView? = null
    val opponent_name: TextView? = null
    val hint: TextView? = null
    val hints = arrayOf("یک مهره قرار بده", "نوبت حریف", "یک مهره از حریف حذف کن")
    val self_profile: ImageView? = null
    val opponent_profile: ImageView? = null
    val btn1: ImageView? = null
    val btn2: ImageView? = null
    val btn3: ImageView? = null
    val btn4: ImageView? = null
    val btn5: ImageView? = null
    val btn6: ImageView? = null
    val btn7: ImageView? = null
    val btn8: ImageView? = null
    val btn9: ImageView? = null
    val btn10: ImageView? = null
    val btn11: ImageView? = null
    val btn12: ImageView? = null
    val btn13: ImageView? = null
    val btn14: ImageView? = null
    val btn15: ImageView? = null
    val btn16: ImageView? = null
    val btn17: ImageView? = null
    val btn18: ImageView? = null
    val btn19: ImageView? = null
    val btn20: ImageView? = null
    val btn21: ImageView? = null
    val btn22: ImageView? = null
    val btn23: ImageView? = null
    val btn24: ImageView? = null
    val turn: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        AnimationUtils.loadAnimation(this, R.anim.fade_in)
                .also { hyper ->
                    findViewById<TextView>(R.id.hint_txt).startAnimation(hyper)
                }
        val type = intent.extras?.getString("type")
        when {
            type.equals("ai") -> {

            }
            type.equals("self") -> {

            }
            else -> {

            }
        }
    }
}
