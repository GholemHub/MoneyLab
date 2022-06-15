package com.gholemhub.moneylab.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.databinding.FragmentCreateCategoryBinding
import com.gholemhub.moneylab.databinding.FragmentImageCategoryBinding
import com.gholemhub.moneylab.model.AppRepository
import com.gholemhub.moneylab.model.AppRepository.Companion.bindingImageCategory

class ImageCategoryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingImageCategory = DataBindingUtil.inflate<FragmentImageCategoryBinding>(inflater,
            R.layout.fragment_image_category, container, false)

        backBtn()


        return bindingImageCategory.root
    }

    private fun backBtn() {
        bindingImageCategory.fabBackToAdd.setOnClickListener {
        Navigation.findNavController(AppRepository.bindingImageCategory.root)
            .navigate(R.id.action_imageCategoryFragment_to_createCategoryFragment)
        }
    }


}