package com.gholemhub.moneylab.adapters

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.classes.TransactionVM
import com.gholemhub.moneylab.model.AppRepository.Companion.userModel

class AdapterTransaction: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {

        return if(userModel.ListOfTransactions[position].id == 1) 1
        else if(userModel.ListOfTransactions[position].id == 3) 0
        else 2
    }

    private fun CheckDataFun(position: Int): String {

        if(userModel.ListOfTransactions.size >=2) {
            //d("TAG", userModel.ListOfTransactions[position].date + "| | " +  userModel.ListOfTransactions[position - 1].date)
            if(userModel.ListOfTransactions[position].date == userModel.ListOfTransactions[position - 1].date){
                return "is equal"
            }

        }
        return "is not equal"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  if(viewType == 1){

            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
            viewHolderInfo(view)

        }else if(viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_transaction, parent, false)
            viewHolderData(view)

        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_line_layout, parent, false)
            viewHolderDeliver(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(userModel.ListOfTransactions[position].id == 1) {
            d("TAG", "ERROR num: " + userModel.ListOfTransactions[position].id)
            (holder as viewHolderInfo).bindItems(userModel.ListOfTransactions[position])
            holder.table.setOnClickListener {

            }
        }
        else if(userModel.ListOfTransactions[position].id == 3) {
            //Log.d("TAG", "HER")

            (holder as AdapterTransaction.viewHolderData).bindItems(userModel.ListOfTransactions[position])
        }


    }

    override fun getItemCount(): Int {
        //d("TAG", "size: ${userModel.ListOfTransactions.size}")
        return userModel.ListOfTransactions.size
    }

    inner class viewHolderInfo(itemView: View): RecyclerView.ViewHolder(itemView){
        var image = itemView.findViewById<ImageView>(R.id.transaction_image)
        var table = itemView.findViewById<TableRow>(R.id.transaction_table)
        var title: TextView = itemView.findViewById(R.id.transaction_expense_text_item)
        var count = itemView.findViewById<TextView>(R.id.transaction_count_text_item)
        var date = itemView.findViewById<TextView>(R.id.transaction_expense_date_item)

        fun bindItems(item : TransactionVM){
            title.text = item.title
            image.setImageResource(item.image)
            count.text = item.count.toString()
            date.text = item.date
        }
    }
    inner class viewHolderData(itemView: View): RecyclerView.ViewHolder(itemView){
        var table = itemView.findViewById<TableRow>(R.id.transaction_data_table)

        fun bindItems(item : TransactionVM){
            //title.text = item.title
        }
    }

    inner class viewHolderDeliver(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems(item : TransactionVM){

        }
    }
}