package com.amk.morris.ui.history

import android.os.Bundle
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
import com.amk.morris.Adapters.HistoryAdapter
import com.amk.morris.Model.HistoryModel
import com.amk.morris.Model.Person
import com.amk.morris.R

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        historyViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_history, container, false)
        historyViewModel.text.observe(this, Observer {

        })
        val recycler = root.findViewById(R.id.history_recycler) as RecyclerView
        val historyList = arrayListOf<HistoryModel>()
        val hmodel = HistoryModel()
        hmodel.date = "98/02/12"
        val self = Person("امیرمحمد کریمی")
        val opponent = Person("آرمان مظلوم زاده")
        self.score = 1202
        opponent.score = 1404
        hmodel.status = "برد"
        hmodel.self = self
        hmodel.opponent = opponent
        historyList.add(hmodel)
        val hmodel2 = HistoryModel()
        hmodel2.date = "98/02/12"
        hmodel2.self = self
        hmodel2.opponent = opponent
        hmodel2.status = "تساوی"
        historyList.add(hmodel2)
        val hmodel3 = HistoryModel()
        hmodel3.date = "98/02/12"
        hmodel3.opponent = opponent
        hmodel3.self = self
        hmodel3.status = "باخت"
        historyList.add(hmodel3)
        val adapter = HistoryAdapter(historyList, context)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val backBtn = root.findViewById<ImageView>(R.id.back_btn)
        backBtn.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        val searchView = SearchView(context)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                adapter.filter.filter(query)
                return false
            }
        })
        return root
    }

}