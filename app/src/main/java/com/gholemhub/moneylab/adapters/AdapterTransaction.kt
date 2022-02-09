package com.gholemhub.moneylab.adapters

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.AddActivity
import com.gholemhub.moneylab.FragmentTransaction
import com.gholemhub.moneylab.FragmentTransaction.Companion.TransactionList
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.viewmodels.TransactionViewModel

class AdapterTransaction: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if(TransactionList[position].id == 1) 0
        else if(TransactionList[position].id == 3) 1
        else 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_transaction, parent, false)
            viewHolderData(view)
        }else if(viewType == 1){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
            viewHolderInfo(view)

        } else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_line_layout, parent, false)
            viewHolderDeliver(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(TransactionList[position].id == 3) {
            d("TAG", "" + TransactionList[position].id)
            (holder as viewHolderInfo).bindItems(TransactionList[position])
            holder.table.setOnClickListener {

            }
        }
        else if(TransactionList[position].id == 1) {
            //Log.d("TAG", "HER")
            (holder as AdapterTransaction.viewHolderData).bindItems(TransactionList[position])
        }
        else if(TransactionList[position].id == 1) {
            //Log.d("TAG", "HER")
            (holder as AdapterTransaction.viewHolderData).bindItems(TransactionList[position])
        }
    }

    override fun getItemCount(): Int {
        return TransactionList.size
    }

    inner class viewHolderInfo(itemView: View): RecyclerView.ViewHolder(itemView){
        var image = itemView.findViewById<ImageView>(R.id.transaction_image)
        var table = itemView.findViewById<TableRow>(R.id.transaction_table)
        var title: TextView = itemView.findViewById<TextView>(R.id.transaction_expense_text_item)
        var count = itemView.findViewById<TextView>(R.id.transaction_count_text_item)

        fun bindItems(item : TransactionViewModel){
            title.text = item.title
            image.setImageResource(item.image)
        }
    }
    inner class viewHolderData(itemView: View): RecyclerView.ViewHolder(itemView){
        var table = itemView.findViewById<TableRow>(R.id.transaction_data_table)
        //var title: TextView = itemView.findViewById<TextView>(R.id.transaction_data1)

        fun bindItems(item : TransactionViewModel){
            //title.text = item.title
        }
    }

    inner class viewHolderDeliver(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems(item : TransactionViewModel){

        }
    }
}