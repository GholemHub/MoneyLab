package com.gholemhub.moneylab.adapters

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.Barrier
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.AddActivity.Companion.TitleType
import com.gholemhub.moneylab.AddActivity.Companion.TitleTypeLine
import com.gholemhub.moneylab.AddActivity.Companion.dialog
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.viewmodels.AdapterViewModel

class AdapterExpense: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
    return if(TitleType[position].type == "expense") 0
    else if(TitleType[position].type == "income") 1
    else if(TitleTypeLine == position) 2
        else 3


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expense_layout, parent, false)
            viewHolderOne(view)
        }else if(viewType == 1){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_income_layout, parent, false)
            viewHolderTwo(view)
        } else if(viewType == 2){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_line_layout, parent, false)
            viewHolderZero(view)
        }
        else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_line_layout, parent, false)
            viewHolderZero(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(TitleType[position].type == "expense") {
            (holder as viewHolderOne).bindItemsOne(TitleType[position])
            holder.table.setOnClickListener {
                dialog.dismiss()
            }
        }
        else if(TitleType[position].type == "income") {
            (holder as viewHolderTwo).bindItemsOne(TitleType[position])
            holder.table.setOnClickListener {
                dialog.dismiss()

            }
        }
        else if(TitleTypeLine == position) {
                d("TAG", "HER")
                (holder as viewHolderZero).bindItemsOne(TitleType[position])
        }

/*
        when {
            position == TitleTypeLine -> {
                (holder as viewHolderZero)
            }
            TitleType[position].type == "income" -> {
                (holder as viewHolderOne).bindItemsOne(TitleType[position])

                holder.table.setOnClickListener {
                    dialog.dismiss();

                }
            }
            TitleType[position].type == "expense" -> {
                (holder as viewHolderTwo).bindItemsOne(TitleType[position])

                holder.table.setOnClickListener {
                    dialog.dismiss();
                }
            }
            else-> 5
        }*/
/*
        if(TitleType[position].type == "income"){
            (holder as viewHolderOne).bindItemsOne(TitleType[position])

            holder.table.setOnClickListener{
                dialog.dismiss();

            }

        }else{
            (holder as viewHolderTwo).bindItemsOne(TitleType[position])
            holder.table.setOnClickListener{
                dialog.dismiss();
            }
        }*/
    }

    override fun getItemCount(): Int {
        return TitleType.size
    }

    inner class viewHolderOne(itemView: View): RecyclerView.ViewHolder(itemView){
        var table = itemView.findViewById<TableRow>(R.id.tytle_expense_table_item)
        var title: TextView = itemView.findViewById<TextView>(R.id.tytle_expense_text_item)

        fun bindItemsOne(item : AdapterViewModel){
            title.text = item.title
        }
    }
    inner class viewHolderTwo(itemView: View): RecyclerView.ViewHolder(itemView){
        var table = itemView.findViewById<TableRow>(R.id.tytle_income_table_item)
        var title: TextView = itemView.findViewById<TextView>(R.id.tytle_income_text_item)


        fun bindItemsOne(item : AdapterViewModel){
            title.text = item.title
        }
    }

    inner class viewHolderZero(itemView: View): RecyclerView.ViewHolder(itemView){


        fun bindItemsOne(item : AdapterViewModel){

        }
    }

}

