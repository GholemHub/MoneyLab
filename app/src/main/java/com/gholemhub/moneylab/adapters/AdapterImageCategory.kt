package com.gholemhub.moneylab.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.model.AddRepository.Companion.ImagesCategoryList

import com.gholemhub.moneylab.model.AddRepository.Companion.addNewCategory
import com.gholemhub.moneylab.model.AppRepository

class AdapterImageCategory: RecyclerView.Adapter<AdapterImageCategory.ViewHolder>() {

    lateinit var imageView: ImageView

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_image,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {

            addNewCategory.image = ImagesCategoryList[position].logo

            //AppRepository.bindingFragmentCreateCategory.tytleImage.setImageResource(ImagesCategoryList[position].logo)


            Navigation.findNavController(AppRepository.bindingImageCategory.root)
                .navigate(R.id.action_imageCategoryFragment_to_createCategoryFragment)
        //d("TAG", "TOAST: ${ImagesCategoryList[position].id}")
        }

        val data = ImagesCategoryList[position]
        imageView = holder.itemView.findViewById(R.id.imageOfCategory)

        imageView.setImageResource(data.logo)
    }

    override fun getItemCount(): Int {
        return ImagesCategoryList.size
    }


}