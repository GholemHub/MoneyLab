package com.gholemhub.moneylab.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.AddActivity.Companion.arrList
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.viewmodels.AdapterViewModel

class AdapterExpense: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {

        return if(position %2 == 0){
            1
        }else{
            0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == 1){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expense_layout, parent, false)
            viewHolderOne(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_income_layout, parent, false)
            viewHolderTwo(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position %2 == 0){
            (holder as viewHolderOne).bindItemsOne(arrList[position])

        }else{
            (holder as viewHolderTwo).bindItemsOne(arrList[position])

        }
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    inner class viewHolderOne(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItemsOne(item : AdapterViewModel){
            var title = itemView.findViewById<TextView>(R.id.tytle_expense_text_item)

            title.text = item.title
        }
    }
    inner class viewHolderTwo(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItemsOne(item : AdapterViewModel){
            var title = itemView.findViewById<TextView>(R.id.tytle_income_text_item)

            title.text = item.title
        }
    }

}
/*
class AdapterExpense: RecyclerView.Adapter<AdapterExpense.ViewHolder>(){

    class ViewHolder(val binding: ItemExpenseLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExpenseLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tytleExpenseTextItem.text = AddActivity.ExpenseItemList[position].name
    }

    override fun getItemCount(): Int {
        return AddActivity.ExpenseItemList.size
    }

}
*/

class ExpenseItem (val image: String, val name: String)