package com.amk.morris.ui.rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amk.morris.R

class RatingFragment : Fragment() {

    private lateinit var ratingViewModel: RatingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        ratingViewModel =
                ViewModelProviders.of(this).get(RatingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_rating, container, false)
//        val textView: TextView = root.findViewById(R.id.)
        ratingViewModel.text.observe(this, Observer {
//            textView.text = it
        })
        return root
    }
}