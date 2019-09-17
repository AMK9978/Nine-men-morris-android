package com.amk.morris

import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.amk.morris.Model.HistoryModel
import com.amk.morris.Model.Person
import com.amk.morris.Model.Player
import com.squareup.picasso.Picasso
import java.io.File
import java.io.IOException

class GameActivity : AppCompatActivity() {

    var self_name: TextView? = null
    var opponent_name: TextView? = null
    var hint: TextView? = null
    var hints = arrayOf("یک مهره قرار بده", "نوبت حریف", "یک مهره از حریف حذف کن")
    var self_profile: ImageView? = null
    var opponent_profile: ImageView? = null
    var turn: Int = 0
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
        //TODO: turn should be received from server

        hint = findViewById(R.id.hint_txt)
        opp_pfree = findViewById(R.id.opp_pfree_txt)
        self_pfree = findViewById(R.id.self_pfree_txt)

        Log.i("TAG", "$selfName vs $oppName and $type")
        val person1 = Person(selfName)
        val person2 = Person(selfName)
        val player1 = Player(applicationContext, person1, 1)
        val player2 = Player(applicationContext, person2, 2)
        player1.drawable = resources.getDrawable(R.drawable.whitemohre_min)
        player1.chosen_drawable = resources.getDrawable(R.drawable.asset9min)
        player2.chosen_drawable = resources.getDrawable(R.drawable.asset10min)
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
        gameRepository.self = turn
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
            when (gameRepository.process(1)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
            Log.i("TAG", "click on 0")
        }
        btn1?.setOnClickListener {
            updatePfrees(gameRepository)
            when (gameRepository.process(2)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
        }
        btn2?.setOnClickListener {
            updatePfrees(gameRepository)
            when (gameRepository.process(3)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
        }
        btn3?.setOnClickListener {
            when (gameRepository.process(4)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
            Log.i("TAG", "click on 3")
        }
        btn4?.setOnClickListener {
            when (gameRepository.process(5)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn5?.setOnClickListener {
            when (gameRepository.process(6)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn6?.setOnClickListener {
            when (gameRepository.process(7)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn7?.setOnClickListener {
            when (gameRepository.process(8)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn8?.setOnClickListener {
            when (gameRepository.process(9)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn9?.setOnClickListener {
            when (gameRepository.process(10)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn10?.setOnClickListener {
            when (gameRepository.process(11)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn11?.setOnClickListener {
            when (gameRepository.process(12)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn12?.setOnClickListener {
            when (gameRepository.process(13)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn13?.setOnClickListener {
            when (gameRepository.process(14)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn14?.setOnClickListener {
            when (gameRepository.process(15)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn15?.setOnClickListener {
            when (gameRepository.process(16)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn16?.setOnClickListener {
            when (gameRepository.process(17)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn17?.setOnClickListener {
            when (gameRepository.process(18)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn18?.setOnClickListener {
            when (gameRepository.process(19)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn19?.setOnClickListener {
            when (gameRepository.process(20)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn20?.setOnClickListener {
            when (gameRepository.process(21)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn21?.setOnClickListener {
            when (gameRepository.process(22)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn22?.setOnClickListener {
            when (gameRepository.process(23)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
        btn23?.setOnClickListener {
            when (gameRepository.process(24)) {
                "firstsong" -> playFirstSong()
                "secondsong" -> playSecondSong()
                "choosesong" -> playChooseSong()
            }
            updatePfrees(gameRepository)
        }
    }

    private fun updatePfrees(gameRepository: GameRepository) {


        opp_pfree?.text = gameRepository.players[1].pfree.toString()
        self_pfree?.text = gameRepository.players[0].pfree.toString()
        hint?.text = gameRepository.game.status
        Log.i("TAG", gameRepository.game.status)
        if (gameRepository.isFinish) {
            val dialog = ReportDialog(this)
            dialog.setCancelable(false)
            dialog.show()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val finishBtn = dialog.findViewById<TextView>(R.id.confirm)
            val statusTxt = dialog.findViewById<Button>(R.id.status_txt)
            val selfName = dialog.findViewById<TextView>(R.id.self_name)
            val oppName = dialog.findViewById<TextView>(R.id.opponent_name)
            val opp = ++turn % 2
            when {
                gameRepository.gameStatus == "win" ->{
                    statusTxt.text = "برد"
                    statusTxt.background = resources.getDrawable(R.drawable.success_bg)
                }
                gameRepository.gameStatus == "lose" -> {
                    statusTxt.text = "باخت"
                    statusTxt.background = resources.getDrawable(R.drawable.fail_bg)
                }
                else -> {
                    statusTxt.text = "تساوی"
                    statusTxt.background = resources.getDrawable(R.drawable.draw_bg)
                }
            }
            selfName.text = gameRepository.players[turn].name
            oppName.text = gameRepository.players[opp].name
            val history = HistoryModel()
            history.status = statusTxt.text.toString()
            history.self = gameRepository.players[turn]
            history.opponent = gameRepository.players[opp]
            history.date = getCurrentDate()
            //TODO: DB or server?

            finishBtn?.setOnClickListener {
                dialog.dismiss()
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    //TODO: Should return current date in shamsi format
    private fun getCurrentDate(): String? {
        return "96/2/12"
    }

    private fun updateHint(text: String) {
        hint?.text = text
    }

    private fun playFirstSong() {

        val firstSong = MediaPlayer()
        try {
            val descriptor: AssetFileDescriptor
            try {
                descriptor = this.assets.openFd("p1.mp3")
                firstSong.setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
                descriptor.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            firstSong.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        Log.i("TAG", "First song")
        firstSong.start()
    }

    private fun playSecondSong() {

        val secondSong = MediaPlayer()
        try {
            val descriptor: AssetFileDescriptor
            try {
                descriptor = this.assets.openFd("p2.mp3")
                secondSong.setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
                descriptor.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            secondSong.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        secondSong.start()
    }

    private fun playChooseSong() {

        val chooseSong = MediaPlayer()
        try {
            val descriptor: AssetFileDescriptor
            try {
                descriptor = this.assets.openFd("procc.mp3")
                chooseSong.setDataSource(descriptor.fileDescriptor, descriptor.startOffset, descriptor.length)
                descriptor.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            chooseSong.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        chooseSong.start()
    }

}
