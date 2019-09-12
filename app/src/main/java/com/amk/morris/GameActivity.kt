package com.amk.morris

import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.amk.morris.Model.Person
import com.amk.morris.Model.Player

class GameActivity : AppCompatActivity() {

    var self_name: TextView? = null
    var opponent_name: TextView? = null
    var hint: TextView? = null
    var hints = arrayOf("یک مهره قرار بده", "نوبت حریف", "یک مهره از حریف حذف کن")
    var self_profile: ImageView? = null
    var opponent_profile: ImageView? = null
    private var btn0: ImageView? = null
    private var btn1: ImageView? = null
    private var btn2: ImageView? = null
    private var btn3: ImageView? = null
    private var btn4: ImageView? = null
    private var btn5: ImageView? = null
    private var btn6: ImageView? = null
    private var btn7: ImageView? = null
    private var btn8: ImageView? = null
    private var btn9: ImageView? = null
    private var btn10: ImageView? = null
    private var btn11: ImageView? = null
    private var btn12: ImageView? = null
    private var btn13: ImageView? = null
    private var btn14: ImageView? = null
    private var btn15: ImageView? = null
    private var btn16: ImageView? = null
    private var btn17: ImageView? = null
    private var btn18: ImageView? = null
    private var btn19: ImageView? = null
    private var btn20: ImageView? = null
    private var btn21: ImageView? = null
    private var btn22: ImageView? = null
    private var btn23: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        AnimationUtils.loadAnimation(this, R.anim.fade_in)
                .also { hyper ->
                    findViewById<TextView>(R.id.hint_txt).startAnimation(hyper)
                }
        val selfName = intent.getStringExtra("selfName")
        val oppName = intent.getStringExtra("oppName")
        val type = intent.getStringExtra("type")

        Log.i("TAG", "$selfName vs $oppName and $type")
        val person1 = Person(selfName)
        val person2 = Person(selfName)
        val player1 = Player(applicationContext, person1, 1)
        val player2 = Player(applicationContext, person2, 2)
        player1.drawable = resources.getDrawable(R.drawable.whitemohre_min)
        player1.chosen_drawable = resources.getDrawable(R.drawable.whitemohre_chosen)
        player2.chosen_drawable = resources.getDrawable(R.drawable.blackmohre_chosen)
        player2.drawable = resources.getDrawable(R.drawable.blackmohre_min)
        val selfName_txt = findViewById<TextView>(R.id.self_name)
        val oppName_txt = findViewById<TextView>(R.id.opponent_name)
        selfName_txt.text = selfName
        oppName_txt.text = oppName

        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btn10 = findViewById(R.id.btn10)
        btn11 = findViewById(R.id.btn11)
        btn12 = findViewById(R.id.btn12)
        btn13 = findViewById(R.id.btn13)
        btn14 = findViewById(R.id.btn14)
        btn15 = findViewById(R.id.btn15)
        btn16 = findViewById(R.id.btn16)
        btn17 = findViewById(R.id.btn17)
        btn18 = findViewById(R.id.btn18)
        btn19 = findViewById(R.id.btn19)
        btn20 = findViewById(R.id.btn20)
        btn21 = findViewById(R.id.btn21)
        btn22 = findViewById(R.id.btn22)
        btn23 = findViewById(R.id.btn23)
        val gameRepository = GameRepository(player1, player2)
        btn0?.setOnClickListener {
            gameRepository.process(0)
        }
        btn1?.setOnClickListener {
            gameRepository.process(1)
        }
        btn2?.setOnClickListener {
            gameRepository.process(2)
        }
        btn3?.setOnClickListener {
            gameRepository.process(3)
        }
        btn4?.setOnClickListener {
            gameRepository.process(4)
        }
        btn5?.setOnClickListener {
            gameRepository.process(5)
        }
        btn6?.setOnClickListener {
            gameRepository.process(6)
        }
        btn7?.setOnClickListener {
            gameRepository.process(7)
        }
        btn8?.setOnClickListener {
            gameRepository.process(8)
        }
        btn9?.setOnClickListener {
            gameRepository.process(9)
        }
        btn10?.setOnClickListener {
            gameRepository.process(10)
        }
        btn11?.setOnClickListener {
            gameRepository.process(11)
        }
        btn12?.setOnClickListener {
            gameRepository.process(12)
        }
        btn13?.setOnClickListener {
            gameRepository.process(13)
        }
        btn14?.setOnClickListener {
            gameRepository.process(14)
        }
        btn15?.setOnClickListener {
            gameRepository.process(15)
        }
        btn16?.setOnClickListener {
            gameRepository.process(16)
        }
        btn17?.setOnClickListener {
            gameRepository.process(17)
        }
        btn18?.setOnClickListener {
            gameRepository.process(18)
        }
        btn19?.setOnClickListener {
            gameRepository.process(19)
        }
        btn20?.setOnClickListener {
            gameRepository.process(20)
        }
        btn21?.setOnClickListener {
            gameRepository.process(21)
        }
        btn22?.setOnClickListener {
            gameRepository.process(22)
        }
        btn23?.setOnClickListener {
            gameRepository.process(23)
        }
    }
}
