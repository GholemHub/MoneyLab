package com.gholemhub.moneylab.model

import com.gholemhub.moneylab.AddActivity
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.classes.Category
import com.gholemhub.moneylab.classes.User
import com.gholemhub.moneylab.databinding.*
import com.gholemhub.moneylab.views.MainActivity
import com.google.firebase.auth.FirebaseAuth

class AddRepository {
    companion object {
        @JvmStatic
        lateinit var AddContext: AddActivity
        lateinit var ImagesCategoryList: MutableList<Category>

    }

    fun CreateImagesCategoryList(){
        ImagesCategoryList = mutableListOf<Category>()

        ImagesCategoryList.add(Category(1, R.drawable.outline_cottage_24))
        ImagesCategoryList.add(Category(2, R.drawable.outline_ramen_dining_24))
        ImagesCategoryList.add(Category(3, R.drawable.outline_cottage_24))
    }

}