package com.gholemhub.moneylab.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.databinding.FragmentCategoryBinding
import com.gholemhub.moneylab.databinding.FragmentChartBinding
import com.gholemhub.moneylab.model.AppRepository
import com.gholemhub.moneylab.model.AppRepository.Companion.bindingFragmentCategory

class CategoryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragmentCategory = DataBindingUtil.inflate<FragmentCategoryBinding>(inflater,
            R.layout.fragment_category, container, false)

        bindingFragmentCategory.fabCategory.setOnClickListener {
            Navigation.findNavController(bindingFragmentCategory.root)
                .navigate(R.id.action_categoryFragment_to_createCategoryFragment)
        }

        return bindingFragmentCategory.root
    }


}