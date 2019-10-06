package com.amk.morris.ui.rating

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amk.morris.API.APIClient
import com.amk.morris.API.APIInterface
import com.amk.morris.Adapters.RatingAdapter
import com.amk.morris.Model.Person
import com.amk.morris.R
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RatingFragment : Fragment() {
    private var persons = arrayListOf<Person>()
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
        val ratingRecycler = root.findViewById(R.id.rating_recycler) as RecyclerView
        val apiInterface = APIClient.getRetrofit().create(APIInterface::class.java)
        val call = apiInterface.ranking() as Call<List<Person>>
        call.enqueue(object : Callback<List<Person>> {
            override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                Log.i("TAG", t.message)
            }

            override fun onResponse(call: Call<List<Person>>, response: Response<List<Person>>) {
                if (response.isSuccessful){
                    persons = response.body() as ArrayList<Person>
                }
            }

        })
        val ratingAdapter = RatingAdapter(persons, context)
        ratingRecycler.adapter = ratingAdapter
        ratingRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val searchView = SearchView(context)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                ratingAdapter.filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                ratingAdapter.filter.filter(query)
                return false
            }
        })
        return root
    }
}