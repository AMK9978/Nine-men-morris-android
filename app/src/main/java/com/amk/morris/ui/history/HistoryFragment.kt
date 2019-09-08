package com.amk.morris.ui.history

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
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
        val recycler = root.findViewById<RecyclerView>(R.id.history_recycler)
        val historyList = arrayListOf<HistoryModel>()
        var hmodel = HistoryModel()
        hmodel.date = "98/02/12"
        val self = Person("امیرمحمد کریمی")
        val opponent = Person("آرمان مظلوم زاده")
        self.rating = 1202
        opponent.rating = 1404
        hmodel.status = "برد"
        hmodel.self = self
        hmodel.opponent = opponent
        historyList.add(hmodel)
        var hmodel2 = HistoryModel()
        hmodel2.date = "98/02/12"
        hmodel2.self = self
        hmodel2.opponent = opponent
        hmodel2.status = "تساوی"
        historyList.add(hmodel2)
        var hmodel3 = HistoryModel()
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
        return root
    }

}