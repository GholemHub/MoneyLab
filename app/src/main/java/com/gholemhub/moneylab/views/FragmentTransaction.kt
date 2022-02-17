package com.gholemhub.moneylab.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.adapters.AdapterTransaction
import com.gholemhub.moneylab.classes.ItemSelectionDecoration
import com.gholemhub.moneylab.databinding.FragmentTransactionBinding
import com.gholemhub.moneylab.model.AppRepository.Companion.userModel
import com.gholemhub.moneylab.views.AuthenticationActivity.Companion.repository


class FragmentTransaction : Fragment() {

    lateinit var adapter1: AdapterTransaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTransactionBinding>(inflater,
            R.layout.fragment_transaction, container, false)

        repository.GetTransactionFromFirestore()
        userModel.ListOfTitles.sortBy { t -> t.id}




        CalculateMoney()


        AdapterSetup(binding)



        return binding.root
    }

    private fun CalculateMoney() {



        (activity as MainActivity).supportActionBar?.title = userModel.showMoney().toString()

    }


    private fun AdapterSetup(binding: FragmentTransactionBinding) {
        repository.GetTransactionFromFirestore()


            var recyclerView: RecyclerView = binding.recyclerViewTransaction
       // var linMan = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        //recyclerView.layoutManager = linMan
        recyclerView.layoutManager = LinearLayoutManager(context)
        //var Diliver = DividerItemDecoration(recyclerView.context, linMan.orientation )
        adapter1 = AdapterTransaction()
        var itemSelectionDecoration = ItemSelectionDecoration()
        //recyclerView.addItemDecoration(itemSelectionDecoration)

        recyclerView.adapter = adapter1

        adapter1.notifyDataSetChanged()

    }
}