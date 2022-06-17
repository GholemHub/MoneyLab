package com.gholemhub.moneylab.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.classes.Category
import com.gholemhub.moneylab.classes.TransactionVM
import com.gholemhub.moneylab.model.AddRepository.Companion.ImagesCategoryList
import com.gholemhub.moneylab.views.MainActivity
import java.util.*

class AdapterImageCategory: RecyclerView.Adapter<AdapterImageCategory.ViewHolder>() {




    lateinit var imageView: ImageView

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //var logo = itemView.findViewById<ImageView>(R.id.imageOfCategory)
        fun setData(item: Category){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_image,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //var r = R.drawable.backspace





        val data = ImagesCategoryList[position]
        imageView = holder.itemView.findViewById(R.id.imageOfCategory)

        imageView.setImageResource(data.logo)
    }

    override fun getItemCount(): Int {
        return ImagesCategoryList.size
    }


}