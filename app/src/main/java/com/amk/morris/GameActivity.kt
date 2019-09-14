package com.amk.morris

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.amk.morris.Model.Person
import com.amk.morris.Model.Player
import com.squareup.picasso.Picasso
import java.io.File

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
    private var self_pfree: TextView? = null
    private var opp_pfree: TextView? = null

    private fun loadProfileImage() {
        val mainFile = File(Environment.getExternalStorageDirectory(), "Morris/profileImage.jpg")
        val oppFile = File(Environment.getExternalStorageDirectory(), "Morris/profileImage.jpg")
        Picasso.get().load(mainFile).fit().placeholder(R.drawable.ic_person_black_24dp).into(self_profile)
        Picasso.get().load(oppFile).fit().placeholder(R.drawable.ic_person_black_24dp).into(opponent_profile)
    }

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
        hint = findViewById(R.id.hint_txt)
        opp_pfree = findViewById(R.id.opp_pfree_txt)
        self_pfree = findViewById(R.id.self_pfree_txt)

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
        self_profile = findViewById(R.id.profile_image)
        opponent_profile = findViewById(R.id.opponent_profile_image)
        loadProfileImage()
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
        gameRepository.board.houses[1].imageView = btn0
        gameRepository.board.houses[2].imageView = btn1
        gameRepository.board.houses[3].imageView = btn2
        gameRepository.board.houses[4].imageView = btn3
        gameRepository.board.houses[5].imageView = btn4
        gameRepository.board.houses[6].imageView = btn5
        gameRepository.board.houses[7].imageView = btn6
        gameRepository.board.houses[8].imageView = btn7
        gameRepository.board.houses[9].imageView = btn8
        gameRepository.board.houses[10].imageView = btn9
        gameRepository.board.houses[11].imageView = btn10
        gameRepository.board.houses[12].imageView = btn11
        gameRepository.board.houses[13].imageView = btn12
        gameRepository.board.houses[14].imageView = btn13
        gameRepository.board.houses[15].imageView = btn14
        gameRepository.board.houses[16].imageView = btn15
        gameRepository.board.houses[17].imageView = btn16
        gameRepository.board.houses[18].imageView = btn17
        gameRepository.board.houses[19].imageView = btn18
        gameRepository.board.houses[20].imageView = btn19
        gameRepository.board.houses[21].imageView = btn20
        gameRepository.board.houses[22].imageView = btn21
        gameRepository.board.houses[23].imageView = btn22
        gameRepository.board.houses[24].imageView = btn23
        btn0?.setOnClickListener {
            gameRepository.process(1)
            updatePfrees(gameRepository)
            Log.i("TAG", "click on 0")
        }
        btn1?.setOnClickListener {
            gameRepository.process(2)
            updatePfrees(gameRepository)
            Log.i("TAG", "click on 1")
        }
        btn2?.setOnClickListener {
            gameRepository.process(3)
            updatePfrees(gameRepository)
            Log.i("TAG", "click on 2")
        }
        btn3?.setOnClickListener {
            gameRepository.process(4)
            updatePfrees(gameRepository)
            Log.i("TAG", "click on 3")
        }
        btn4?.setOnClickListener {
            gameRepository.process(5)
            updatePfrees(gameRepository)
        }
        btn5?.setOnClickListener {
            gameRepository.process(6)
            updatePfrees(gameRepository)
        }
        btn6?.setOnClickListener {
            gameRepository.process(7)
            updatePfrees(gameRepository)
        }
        btn7?.setOnClickListener {
            gameRepository.process(8)
            updatePfrees(gameRepository)
        }
        btn8?.setOnClickListener {
            gameRepository.process(9)
            updatePfrees(gameRepository)
        }
        btn9?.setOnClickListener {
            gameRepository.process(10)
            updatePfrees(gameRepository)
        }
        btn10?.setOnClickListener {
            gameRepository.process(11)
            updatePfrees(gameRepository)
        }
        btn11?.setOnClickListener {
            gameRepository.process(12)
            updatePfrees(gameRepository)
        }
        btn12?.setOnClickListener {
            gameRepository.process(13)
            updatePfrees(gameRepository)
        }
        btn13?.setOnClickListener {
            gameRepository.process(14)
            updatePfrees(gameRepository)
        }
        btn14?.setOnClickListener {
            gameRepository.process(15)
            updatePfrees(gameRepository)
        }
        btn15?.setOnClickListener {
            gameRepository.process(16)
            updatePfrees(gameRepository)
        }
        btn16?.setOnClickListener {
            gameRepository.process(17)
            updatePfrees(gameRepository)
        }
        btn17?.setOnClickListener {
            gameRepository.process(18)
            updatePfrees(gameRepository)
        }
        btn18?.setOnClickListener {
            gameRepository.process(19)
            updatePfrees(gameRepository)
        }
        btn19?.setOnClickListener {
            gameRepository.process(20)
            updatePfrees(gameRepository)
        }
        btn20?.setOnClickListener {
            gameRepository.process(21)
            updatePfrees(gameRepository)
        }
        btn21?.setOnClickListener {
            gameRepository.process(22)
            updatePfrees(gameRepository)
        }
        btn22?.setOnClickListener {
            gameRepository.process(23)
            updatePfrees(gameRepository)
        }
        btn23?.setOnClickListener {
            gameRepository.process(24)
            updatePfrees(gameRepository)
        }
    }

    private fun updatePfrees(gameRepository: GameRepository) {
        opp_pfree?.text = gameRepository.players[1].pnum.toString()
        self_pfree?.text = gameRepository.players[0].pnum.toString()

    }

    private fun updateHint(text: String) {
        hint?.setText(text)
    }
}
