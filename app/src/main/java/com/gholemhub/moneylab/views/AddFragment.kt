package com.gholemhub.moneylab.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gholemhub.moneylab.AddActivity.Companion.binding
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.databinding.FragmentChartBinding

class AddFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bindingFragmentChart = DataBindingUtil.inflate<FragmentChartBinding>(inflater,
            R.layout.fragment_chart, container, false)

        // Inflate the layout for this fragment
        return bindingFragmentChart.root
    }

}