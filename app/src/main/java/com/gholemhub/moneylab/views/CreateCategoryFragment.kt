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
import androidx.databinding.DataBindingUtil
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.databinding.FragmentAddBinding
import com.gholemhub.moneylab.databinding.FragmentCreateCategoryBinding
import com.gholemhub.moneylab.model.AppRepository
import com.gholemhub.moneylab.model.AppRepository.Companion.bindingFragmentCreateCategory

class CreateCategoryFragment : Fragment() {

private var income: Boolean = false
private var excome: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragmentCreateCategory = DataBindingUtil.inflate<FragmentCreateCategoryBinding>(inflater,
            R.layout.fragment_create_category, container, false)



        bindingFragmentCreateCategory.Income.setOnClickListener {
            ChangeColorBtn(bindingFragmentCreateCategory.Income, bindingFragmentCreateCategory.Excome)

            //it.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#32A852"))
            //bindingFragmentCreateCategory.Income.setTextColor(Color.WHITE)
        }
        bindingFragmentCreateCategory.Excome.setOnClickListener {
            ChangeColorBtn(bindingFragmentCreateCategory.Excome,bindingFragmentCreateCategory.Excome)

            //it.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            //bindingFragmentCreateCategory.Excome.setTextColor(Color.parseColor("#32A852"))
        }

        return bindingFragmentCreateCategory.root
    }
var b = true
    private fun ChangeColorBtn(btn: Button, btn2: Button) {

        //d("TAG", "$b")

        if(b){
            btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#32A852"))
            btn.setTextColor(Color.WHITE)

            btn2.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            btn2.setTextColor(Color.parseColor("#32A852"))
            d("TAG", "$b")
        }else{
            btn2.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#32A852"))
            btn2.setTextColor(Color.WHITE)

            btn.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            btn.setTextColor(Color.parseColor("#32A852"))
            d("TAG", "$b")
        }
        b = !b
        /*

        if(income && !excome){
            btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#32A852"))
            btn.setTextColor(Color.WHITE)

            btn2.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            btn2.setTextColor(Color.parseColor("#32A852"))
            income = !income
            excome = !excome
        }
        else{
            btn.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            btn.setTextColor(Color.parseColor("#32A852"))

            btn2.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#32A852"))
            btn2.setTextColor(Color.WHITE)


            income = !income
            excome = !excome
        }
*/

    }


}