package com.amk.morris.ui.home

import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.os.Handler
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
import com.amk.morris.ui.edit.EditFragment
import com.amk.morris.ui.history.HistoryFragment
import com.amk.morris.ui.rating.RatingFragment
import com.amk.morris.ui.settings.SettingsFragment
import com.squareup.picasso.Picasso
import java.io.File

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
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

        val back_image = root.findViewById<ImageView>(R.id.back_image1)
        ObjectAnimator.ofFloat(back_image, View.ROTATION, 0f, 360f).setDuration(60000).start()
        val play_req = root.findViewById<Button>(R.id.play_request)
        play_req.setOnClickListener {
            val dialog = context?.let { it1 -> Dialog(it1) }
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setContentView(R.layout.game_options)
            dialog?.setCancelable(true)
            dialog?.show()
            val onlineGame = dialog?.findViewById<TextView>(R.id.online_game)
            val aiGame = dialog?.findViewById<TextView>(R.id.ai_game)
            val selfGame = dialog?.findViewById<TextView>(R.id.self_game)
            val selfName = context?.getSharedPreferences("pref", Context.MODE_PRIVATE)!!.getString("name", "من!") as String
            onlineGame?.setOnClickListener {
                dialog.dismiss()
                val dialog2 = context?.let { it1 -> Dialog(it1) }
                Handler().postDelayed({
                    dialog2?.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    dialog2?.setContentView(R.layout.waiting_for_opponent)
                    dialog2?.setCancelable(false)
                    dialog2?.show()
                }, 2000)
                //TODO: Send request to the server for notice that current user is ready, wait until responds
                dialog2?.dismiss()
                val goto = Intent(this.context, GameActivity::class.java)
                goto.putExtra("type", "online")
                goto.putExtra("selfName", selfName)
                startActivity(goto)
            }
            aiGame?.setOnClickListener {
                dialog.dismiss()
                val goto = Intent(activity, GameActivity::class.java)
                goto.putExtra("type", "ai")
                goto.putExtra("selfName", selfName)
                startActivity(goto)
            }

            selfGame?.setOnClickListener {
                dialog.dismiss()
                val goto = Intent(activity, GameActivity::class.java)
                goto.putExtra("type", "self")
                goto.putExtra("selfName", selfName)
                goto.putExtra("oppName", selfName)
                startActivity(goto)
            }

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


    private fun replaceFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_host_fragment, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
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