package com.amk.morris.ui.edit

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amk.morris.R

class EditFragment : Fragment() {

    private lateinit var editViewModel: EditViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        editViewModel =
                ViewModelProviders.of(this).get(EditViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_edit, container, false)
        val backBtn = root.findViewById<ImageView>(R.id.back_btn)
        backBtn.setOnClickListener {
            fragmentManager?.popBackStack()
        }


        val backImage = root.findViewById<ImageView>(R.id.imageView4)
        ObjectAnimator.ofFloat(backImage, View.ROTATION, 0f, 360f).setDuration(60000).start()

//        val textView: TextView = root.findViewById(R.id.text_slideshow)
        editViewModel.text.observe(this, Observer {
//            textView.text = it
        })
        return root
    }
}