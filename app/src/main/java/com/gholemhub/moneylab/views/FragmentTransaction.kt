package com.gholemhub.moneylab.views

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.util.Log.d
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
import com.gholemhub.moneylab.model.AppRepository.Companion.repository
import com.gholemhub.moneylab.model.AppRepository.Companion.userModel



class FragmentTransaction : Fragment() {

    lateinit var adapter1: AdapterTransaction

lateinit var binding: FragmentTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentTransactionBinding>(inflater,
            R.layout.fragment_transaction, container, false)




        //SystemClock.sleep(7000);

        userModel.ListOfTitles.sortBy { t -> t.id}


        if(userModel == null) {
            d("TAG", "EEEEEEEEE")
        }else{
            //userModel.ListOfTransactions
            d("TAG", "NNNNNNN ${userModel.ListOfTransactions}")
        }
        //d("TAG", "list of titles: ${userModel.ListOfTitles[0]}")

        CalculateMoney()


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AdapterSetup(binding)
    }

    private fun CalculateMoney() {

        d("TAG", "Money of user" + userModel.showMoney().toString())
        (activity as MainActivity).supportActionBar?.title = userModel.showMoney().toString()


    }


    @SuppressLint("NotifyDataSetChanged")
    private fun AdapterSetup(binding: FragmentTransactionBinding) {
        repository.GetTransactionFromFirestore()


        var recyclerView: RecyclerView = binding.recyclerViewTransaction
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter1 = AdapterTransaction()
        var itemSelectionDecoration = ItemSelectionDecoration()
        //recyclerView.addItemDecoration(itemSelectionDecoration)
        //adapter1.notifyDataSetChanged()
        recyclerView.adapter = adapter1

        d("TAG", "Adapter 1")
        //(recyclerView.adapter as AdapterTransaction).notifyDataSetChanged()
        adapter1.notifyDataSetChanged()





    }
}