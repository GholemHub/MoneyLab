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
import com.gholemhub.moneylab.databinding.FragmentChartBinding
import com.gholemhub.moneylab.databinding.FragmentTransactionBinding
import com.gholemhub.moneylab.model.AppRepository.Companion.repository
import com.gholemhub.moneylab.model.AppRepository.Companion.userModel



class FragmentTransaction : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }



}