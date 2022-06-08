package com.gholemhub.moneylab.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView



import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.model.AppRepository.Companion.userModel
import com.gholemhub.moneylab.classes.TitleIE
import com.gholemhub.moneylab.model.AppRepository.Companion.bindingFragmentAdd
import com.gholemhub.moneylab.model.AppRepository.Companion.bindingFragmentCategory
import java.lang.ClassCastException

class AdapterAdd: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var listener: DialogAddListener

    override fun getItemViewType(position: Int): Int {
    return if(userModel.ListOfTitles[position].id == 3) 0
    else if(userModel.ListOfTitles[position].id == 1) 1
    else if(userModel.ListOfTitles[position].id == 2) 2
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

        if(userModel.ListOfTitles[position].id == 3) {
            (holder as viewHolderOne).bindItemsOne(userModel.ListOfTitles[position])
            holder.table.setOnClickListener {
                listener.applyTipe(userModel.ListOfTitles[position])
                bindingFragmentAdd.tytleImage.setImageResource(userModel.ListOfTitles[position].image)
                //dialog.dismiss()
            }
        }
        else if(userModel.ListOfTitles[position].id == 1) {
            (holder as viewHolderTwo).bindItemsOne(userModel.ListOfTitles[position])
            holder.table.setOnClickListener {

                listener.applyTipe(userModel.ListOfTitles[position])
                bindingFragmentAdd.tytleImage.setImageResource(userModel.ListOfTitles[position].image)
                //dialog.dismiss()

            }
        }
        else if(userModel.ListOfTitles[position].id == 2) {
                //d("TAG", "HER")
                (holder as viewHolderZero).bindItemsOne(userModel.ListOfTitles[position])
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        try {
            listener = recyclerView.context as DialogAddListener
        } catch (e: ClassCastException) {
            throw ClassCastException(recyclerView.context.toString() + "implement the DialogListener")
        }
    }

    override fun getItemCount(): Int {
        return userModel.ListOfTitles.size
    }

    inner class viewHolderOne(itemView: View): RecyclerView.ViewHolder(itemView){
        var image = itemView.findViewById<ImageView>(R.id.tytle_expense_image_item)
        var table = itemView.findViewById<TableRow>(R.id.tytle_expense_table_item)
        var title = itemView.findViewById<TextView>(R.id.tytle_expense_text_item)

        fun bindItemsOne(item : TitleIE){
            title.text = item.title
            image.setImageResource(item.image)
        }
    }
    inner class viewHolderTwo(itemView: View): RecyclerView.ViewHolder(itemView){
        var image = itemView.findViewById<ImageView>(R.id.tytle_income_image_item)
        var table = itemView.findViewById<TableRow>(R.id.tytle_income_table_item)
        var title = itemView.findViewById<TextView>(R.id.tytle_income_text_item)


        fun bindItemsOne(item : TitleIE){
            title.text = item.title
            image.setImageResource(item.image)
        }
    }

    inner class viewHolderZero(itemView: View): RecyclerView.ViewHolder(itemView){


        fun bindItemsOne(item : TitleIE){

        }
    }


    interface DialogAddListener{
        fun applyTipe(id: TitleIE){}
    }

}

