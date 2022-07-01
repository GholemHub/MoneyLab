package com.gholemhub.moneylab.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation

import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.databinding.FragmentAddBinding
import com.gholemhub.moneylab.model.AppRepository.Companion.bindingFragmentAdd

class AddFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragmentAdd = DataBindingUtil.inflate<FragmentAddBinding>(inflater,
            R.layout.fragment_add, container, false)

        bindingFragmentAdd.tytleImage.setOnClickListener {


            Navigation.findNavController(bindingFragmentAdd.root)
                .navigate(R.id.action_addFragment2_to_categoryFragment)
        }

        // Inflate the layout for this fragment
        return bindingFragmentAdd.root
    }

}