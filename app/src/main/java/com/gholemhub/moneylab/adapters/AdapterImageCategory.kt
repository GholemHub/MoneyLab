package com.gholemhub.moneylab.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.classes.Category
import com.gholemhub.moneylab.classes.TransactionVM
import java.util.*

class AdapterImageCategory: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var ImagesCategoryList = mutableListOf<Category>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var logo = itemView.findViewById<ImageView>(R.id.imageOfCategory)
        fun bingItems(item: Category){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return ImagesCategoryList.size
    }

}