package com.gholemhub.moneylab.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.adapters.AdapterTransaction
import com.gholemhub.moneylab.classes.ItemSelectionDecoration
import com.gholemhub.moneylab.databinding.FragmentChartBinding
import com.gholemhub.moneylab.databinding.FragmentTransactionBinding
import com.gholemhub.moneylab.model.AppRepository
import com.gholemhub.moneylab.model.AppRepository.Companion.bindingFragmentChart

class FragmentChart : Fragment() {

    lateinit var adapter1: AdapterTransaction

    //lateinit var bindingFragmentChart: FragmentChartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingFragmentChart = DataBindingUtil.inflate<FragmentChartBinding>(inflater,
            R.layout.fragment_chart, container, false)




        //SystemClock.sleep(7000);


        AppRepository?.userModel?.ListOfTitles?.sortBy { t -> t.id}


        if(AppRepository.userModel == null) {
            Log.d("TAG", "EEEEEEEEE")
        }else{
            //userModel.ListOfTransactions
            Log.d("TAG", "NNNNNNN ${AppRepository.userModel.ListOfTransactions}")
        }
        //d("TAG", "list of titles: ${userModel.ListOfTitles[0]}")

        CalculateMoney()


        return bindingFragmentChart.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AdapterSetup(bindingFragmentChart)
    }

    private fun CalculateMoney() {

        Log.d("TAG", "Money of user" + AppRepository.userModel.showMoney().toString())
        (activity as MainActivity).supportActionBar?.title = AppRepository.userModel.showMoney().toString()


    }


    @SuppressLint("NotifyDataSetChanged")
    private fun AdapterSetup(binding: FragmentChartBinding) {
        AppRepository.repository.GetTransactionFromFirestore()


        var recyclerView: RecyclerView = binding.recyclerViewTransaction
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter1 = AdapterTransaction()
        //var itemSelectionDecoration = ItemSelectionDecoration()
        //recyclerView.addItemDecoration(itemSelectionDecoration)
        //adapter1.notifyDataSetChanged()
        recyclerView.adapter = adapter1

        Log.d("TAG", "Adapter 1")
        //(recyclerView.adapter as AdapterTransaction).notifyDataSetChanged()
        adapter1.notifyDataSetChanged()

    }
}