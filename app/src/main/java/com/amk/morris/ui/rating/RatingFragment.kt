package com.amk.morris.ui.rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amk.morris.Adapters.RatingAdapter
import com.amk.morris.Model.Person
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
        val backBtn = root.findViewById<ImageView>(R.id.back_btn)
        backBtn.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        val searchBtn = root.findViewById<ImageView>(R.id.search_btn)
        searchBtn.setOnClickListener {

        }
        val ratingRecycler = root.findViewById<RecyclerView>(R.id.rating_recycler)
        val persons = arrayListOf<Person>()
        val person = Person("اکبر ترینم")
        person.rating = 1202
        person.id = 1
        val person2 = Person("امیر ترینم")
        person2.rating = 1402
        person2.id = 2

        persons.add(person)
        persons.add(person2)
        val ratingAdapter = RatingAdapter(persons, context)
        ratingRecycler.adapter = ratingAdapter
        ratingRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return root
    }
}