package com.gholemhub.moneylab.adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.AddActivity.Companion.IncomeItemList
import com.gholemhub.moneylab.databinding.ActivityAddBinding

import com.gholemhub.moneylab.databinding.ItemIncomeLayoutBinding

class AdapterIncome: RecyclerView.Adapter<AdapterIncome.ViewHolder>(){

    class ViewHolder(val binding: ItemIncomeLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemIncomeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tytleIncomeTextItem.text = IncomeItemList[position].name
    }

    override fun getItemCount(): Int {
        return IncomeItemList.size
    }
}

class IncomeItem (val image: String, val name: String)
