package com.amk.morris.ui.home

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amk.morris.GameActivity
import com.amk.morris.Login
import com.amk.morris.R
import com.amk.morris.SocketAct
import com.amk.morris.ui.edit.EditFragment
import com.amk.morris.ui.history.HistoryFragment
import com.amk.morris.ui.rating.RatingFragment
import com.amk.morris.ui.settings.SettingsFragment
import com.github.nkzawa.socketio.client.IO
import com.squareup.picasso.Picasso
import java.io.*
import java.net.ServerSocket
import java.net.Socket

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var socket: Socket? = null
    private var a = 0
    private var end = false
    private var sendMessageThread: Thread? = null
    private var getMessageThread: Thread? = null
    private var dialog: Dialog? = null
    private var dialog2: Dialog? = null
    private var profileImage: ImageView? = null
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(this, Observer {
            //            textView.text = it
        })

        val backImage = root.findViewById<ImageView>(R.id.back_image1)
        ObjectAnimator.ofFloat(backImage, View.ROTATION, 0f, 360f).setDuration(60000).start()
        val playReq = root.findViewById<Button>(R.id.play_request)
        playReq.setOnClickListener {
            dialog = context?.let { it1 -> Dialog(it1) }
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setContentView(R.layout.game_options)
            dialog?.setCancelable(true)
            dialog?.show()
            val onlineGame = dialog?.findViewById<TextView>(R.id.online_game)
            val aiGame = dialog?.findViewById<TextView>(R.id.ai_game)
            val selfGame = dialog?.findViewById<TextView>(R.id.self_game)
            val selfName = activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)!!.getString("name", "من!") as String
            Log.i("TAG", "NAME: $selfName")
            onlineGame?.setOnClickListener {
                dialog?.dismiss()
//                dialog2 = context?.let { it1 -> Dialog(it1) }
//                dialog2?.requestWindowFeature(Window.FEATURE_NO_TITLE)
//                dialog2?.setContentView(R.layout.waiting_for_opponent)
//                dialog2?.setCancelable(false)
//                dialog2?.show()
//                runSocket()

//                sendMessage("haji")
                val ii = Intent(context, SocketAct::class.java);
                startActivity(ii)

//                Handler().postDelayed({
//                    //TODO: Send request to the server for notice that current user is ready, wait until responds
//                    dialog2?.dismiss()
//                    val goto = Intent(this.context, GameActivity::class.java)
//                    goto.putExtra("type", "online")
//                    goto.putExtra("selfName", selfName)
//                    startActivity(goto)
//                }, 2000)

//                Handler().postDelayed({
//                    dialog2?.dismiss()
//                },4000)
//                dialog2?.requestWindowFeature(Window.FEATURE_NO_TITLE)
//                dialog2?.setContentView(R.layout.waiting_for_opponent)
//                dialog2?.setCancelable(false)
//                dialog2?.show()
            }
            aiGame?.setOnClickListener {
                dialog?.dismiss()
                val goto = Intent(activity, GameActivity::class.java)
                goto.putExtra("type", "ai")
                goto.putExtra("selfName", selfName)
                startActivity(goto)
            }

            selfGame?.setOnClickListener {
                dialog?.dismiss()
                val goto = Intent(activity, GameActivity::class.java)
                goto.putExtra("type", "self")
                goto.putExtra("selfName", selfName)
                goto.putExtra("oppName", selfName)
                startActivity(goto)
            }

        }
        if (a == 1) {
//            dialog?.dismiss()

            val selfName = activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)!!.getString("name", "من!") as String
            val goto = Intent(this.context, GameActivity::class.java)
            goto.putExtra("type", "online")
            goto.putExtra("selfName", selfName)
            startActivity(goto)
            activity!!.finish()
        }
        val rating = root.findViewById<Button>(R.id.rating_btn)
        val edit = root.findViewById<Button>(R.id.edit_btn)
        val history = root.findViewById<Button>(R.id.history_btn)
        val settings = root.findViewById<Button>(R.id.settings_btn)
        val logout = root.findViewById<Button>(R.id.logout_btn)
        profileImage = root.findViewById(R.id.profile_image)
        rating.setOnClickListener {
            replaceFragment(fragment = RatingFragment())
        }
        edit.setOnClickListener {
            replaceFragment(fragment = EditFragment())
        }
        settings.setOnClickListener {
            replaceFragment(fragment = SettingsFragment())
        }
        history.setOnClickListener {
            replaceFragment(fragment = HistoryFragment())
        }

        logout.setOnClickListener {
            startActivity(Intent(context, Login::class.java))
            //TODO: Remove JWT
            activity?.finish()
        }
        loadProfileImage()

        return root
    }


    private fun startServerSocket() {
        val thread = Thread(object : Runnable {
            private var stringData: String? = null
            override fun run() {
                try {
                    val ss = ServerSocket(9002)
                    while (!end) {
                        //Server is waiting for client here, if needed
                        val s = ss.accept()
                        val input = BufferedReader(InputStreamReader(s.getInputStream()))
                        val output = PrintWriter(s.getOutputStream())
                        stringData = input.readLine()
                        output.println("FROM SERVER - " + stringData!!.toUpperCase())
                        output.flush()

                        try {
                            Thread.sleep(1000)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                        updateUI(stringData)
                        if (stringData!!.equals("STOP", ignoreCase = true)) {
                            end = true
                            output.close()
                            s.close()
                            break
                        }

                        output.close()
                        s.close()
                    }
                    ss.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

        })
        thread.start()
    }

    private fun updateUI(stringData: String?) {
        val handler = Handler()
        handler.post {
            val s = "akbaram"
            if (stringData!!.trim { it <= ' ' }.isNotEmpty())
                Log.i("TAG", "$s\nFrom Client : $stringData")
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class Async : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg p0: Unit?) {
            Log.i("TAG", "In thread")

        }
    }

    private fun sendMessage(message: String) {

        val handler = Handler()
        val thread = Thread(Runnable {
            try {
                //Replace below IP with the IP of that device in which server socket open.
                //If you change port then change the port number in the server side code also.
                val s = Socket("xxx.xxx.xxx.xxx", 9002)
                val out = s.getOutputStream()
                val output = PrintWriter(out)
                output.flush()
                val input = BufferedReader(InputStreamReader(s.getInputStream()))
                val st = input.readLine()

                handler.post {
                    if (st.trim { it <= ' ' }.isNotEmpty()) {
                        Log.i("TAG", st)
                    }
                }

                output.close()
                out.close()
                s.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        })
        thread.start()
    }

    private fun runSocket() {
        Log.i("TAG", "in runSocket")
        Async().execute()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_host_fragment, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun getMessage() {
        try {
            socket.use {
                var responseString: String? = null
                val bufferReader = BufferedReader(InputStreamReader(it!!.inputStream))
                Log.i("TAG", "Inja1")
                while (true) {
                    Log.i("TAG", "in while")
                    val line = bufferReader.readLine() ?: break
                    responseString += line
                    // oppname, opId , playerNumber
                    Log.i("TAG", line)
                    if (line == "exit") {
                        break
                    }
                }
                println("Received: $responseString")
                a = 1
            }
        } catch (he: java.lang.Exception) {
            Log.i("TAG", "Error!")
            Log.i("TAG", he.message)
            he.printStackTrace()
        }
    }


    private fun loadProfileImage() {
        val mainFile = File(Environment.getExternalStorageDirectory(), "Morris/profileImage.jpg")
        Picasso.get().load(mainFile).fit().placeholder(R.drawable.ic_person_black_24dp).into(profileImage)
    }

    class WaitingDialog(context: Context?) : Dialog(context!!) {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.waiting_for_opponent)
        }
    }
}