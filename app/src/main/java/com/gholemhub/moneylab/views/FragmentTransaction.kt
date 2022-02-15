package com.gholemhub.moneylab.views

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.adapters.AdapterTransaction

import com.gholemhub.moneylab.databinding.FragmentTransactionBinding
import com.gholemhub.moneylab.model.AppRepository.Companion.userModel
import com.gholemhub.moneylab.classes.TransactionVM
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



        //d("TAG", "Size: ${userModel.ListOfTransactions.size}")

        AdapterSetup(binding)



        return binding.root
    }



    private fun AdapterSetup(binding: FragmentTransactionBinding) {
        repository.GetTransactionFromFirestore()

            var tytleIncome: RecyclerView = binding.recyclerViewTransaction
            tytleIncome.layoutManager = LinearLayoutManager(context)
            adapter1 = AdapterTransaction()
            tytleIncome.adapter = adapter1

            adapter1.notifyDataSetChanged()

    }
}