package com.gholemhub.moneylab.views

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.adapters.AdapterAdd
import com.gholemhub.moneylab.databinding.FragmentAddBinding
import com.gholemhub.moneylab.databinding.FragmentCreateCategoryBinding
import com.gholemhub.moneylab.model.AddRepository.Companion.AddContext
import com.gholemhub.moneylab.model.AddRepository.Companion.transactionAddRep
import com.gholemhub.moneylab.model.AppRepository
import com.gholemhub.moneylab.model.AppRepository.Companion.bindingFragmentCreateCategory

class CreateCategoryFragment : Fragment() {

private var choise: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragmentCreateCategory = DataBindingUtil.inflate<FragmentCreateCategoryBinding>(inflater,
            R.layout.fragment_create_category, container, false)

        IncomeExcomeListeners()
        ImageListener()

        CreateListener()

        return bindingFragmentCreateCategory.root
    }

    private fun ImageListener() {
        if(transactionAddRep.image != null && transactionAddRep.image != 0) {

            d("TAG", "First: ${transactionAddRep.image}")

            bindingFragmentCreateCategory.tytleImage.setImageResource(transactionAddRep.image)
        }else{
            d("TAG", " second: ${transactionAddRep.image}")
            bindingFragmentCreateCategory.tytleImage.setImageResource(R.drawable.outline_add_photo_alternate_24)
        }
        bindingFragmentCreateCategory.tytleImage.setOnClickListener{
            Navigation.findNavController(bindingFragmentCreateCategory.root)
                .navigate(R.id.action_createCategoryFragment_to_imageCategoryFragment)
        }
    }

    private fun CreateListener() {
        bindingFragmentCreateCategory.Create.setOnClickListener {

            transactionAddRep.id = 1
            if(bindingFragmentCreateCategory.inputText != null && bindingFragmentCreateCategory.inputText.toString().isEmpty()){
                transactionAddRep.title = bindingFragmentCreateCategory.inputText.toString()
            }



            Navigation.findNavController(bindingFragmentCreateCategory.root)
                .navigate(R.id.action_createCategoryFragment_to_categoryFragment)
        }
    }

    private fun IncomeExcomeListeners() {
        bindingFragmentCreateCategory.Excome.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#32A852"))
        bindingFragmentCreateCategory.Excome.setTextColor(Color.WHITE)
        bindingFragmentCreateCategory.Income.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        bindingFragmentCreateCategory.Income.setTextColor(Color.parseColor("#32A852"))


        bindingFragmentCreateCategory.Income.setOnClickListener {
                ChangeColorBtn(
                    bindingFragmentCreateCategory.Income,
                    bindingFragmentCreateCategory.Excome
                )
        }

        bindingFragmentCreateCategory.Excome.setOnClickListener {
                ChangeColorBtn(
                    bindingFragmentCreateCategory.Income,
                    bindingFragmentCreateCategory.Excome
                )
        }
    }

    private fun ChangeColorBtn(btn: Button, btn2: Button) {


            if(choise){
                btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#32A852"))
                btn.setTextColor(Color.WHITE)

                btn2.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
                btn2.setTextColor(Color.parseColor("#32A852"))
                d("TAG", "$choise")
            }else{
                btn2.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#32A852"))
                btn2.setTextColor(Color.WHITE)

                btn.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
                btn.setTextColor(Color.parseColor("#32A852"))
                d("TAG", "$choise")
            }
            choise = !choise
    }
}