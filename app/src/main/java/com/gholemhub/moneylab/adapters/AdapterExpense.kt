package com.gholemhub.moneylab.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.AddActivity
import com.gholemhub.moneylab.databinding.ItemExpenseLayoutBinding

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

class ExpenseItem (val image: String, val name: String)