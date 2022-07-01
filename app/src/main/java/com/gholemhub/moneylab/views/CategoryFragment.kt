package com.gholemhub.moneylab.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.adapters.AdapterAdd
import com.gholemhub.moneylab.adapters.AdapterImageCategory
import com.gholemhub.moneylab.databinding.FragmentCategoryBinding
import com.gholemhub.moneylab.model.AddRepository
import com.gholemhub.moneylab.model.AppRepository
import com.gholemhub.moneylab.model.AppRepository.Companion.bindingFragmentCategory
import com.gholemhub.moneylab.model.AppRepository.Companion.repository
import com.gholemhub.moneylab.model.AppRepository.Companion.userModel


class CategoryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragmentCategory = DataBindingUtil.inflate<FragmentCategoryBinding>(inflater,
            R.layout.fragment_category, container, false)

        CreateRecyclerView()

// Override backBTN
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_categoryFragment_to_addFragment2)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        bindingFragmentCategory.fabCategory.setOnClickListener {
            Navigation.findNavController(bindingFragmentCategory.root)
                .navigate(R.id.action_categoryFragment_to_createCategoryFragment)
        }

        return bindingFragmentCategory.root
    }

    private fun CreateRecyclerView() {

        repository.GetListOfCategoryFromFirestore()
        //d("TAG","lIST OF CATEGORY ${userModel.ListOfCategoryes[0].title}" )

        var adapter = AdapterAdd()
        bindingFragmentCategory.recyclerView.adapter = adapter
        bindingFragmentCategory.recyclerView.layoutManager =
            GridLayoutManager(AddRepository.AddContext, 2, GridLayoutManager.VERTICAL, false)

    }


}