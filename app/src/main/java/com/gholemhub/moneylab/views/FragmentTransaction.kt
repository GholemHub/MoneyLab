package com.gholemhub.moneylab.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.AddActivity
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.adapters.AdapterAddDialog
import com.gholemhub.moneylab.adapters.AdapterTransaction

import com.gholemhub.moneylab.databinding.FragmentTransactionBinding
import com.gholemhub.moneylab.viewmodels.TransactionViewModel

class FragmentTransaction : Fragment() {

    companion object {
        @JvmStatic
        var TransactionList = mutableListOf<TransactionViewModel>()
    }

    lateinit var adapter1: AdapterTransaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTransactionBinding>(inflater,
            R.layout.fragment_transaction, container, false)

        AddActivity.TitleType.sortBy { t -> t.id}

        AdapterSetup(binding)


        return binding.root
//        return inflater.inflate(R.layout.fragment_add, container, false)
    }



    private fun AdapterSetup(binding: FragmentTransactionBinding) {

            var tytleIncome: RecyclerView = binding.recyclerViewTransaction
            tytleIncome.layoutManager = LinearLayoutManager(context)
            adapter1 = AdapterTransaction()
            tytleIncome.adapter = adapter1

            adapter1.notifyDataSetChanged()

    }
}