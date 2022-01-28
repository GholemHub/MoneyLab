package com.gholemhub.moneylab

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.adapters.AdapterIncome
import com.gholemhub.moneylab.databinding.ActivityMainBinding
import com.gholemhub.moneylab.databinding.DialogTytleBinding

class Tytle_Dialig: AppCompatDialogFragment() {



    var binding = DialogTytleBinding.inflate(layoutInflater)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var viewBind = binding.root



        var builder = AlertDialog.Builder(activity)

        var inflater = activity?.layoutInflater
        var view = inflater?.inflate(R.layout.dialog_tytle, null)

        builder.setView(view)
            .setTitle("Choose tytle")
            .setPositiveButton("Done") { dialog, which ->

            }

        return builder.create()
    }
/*
    fun RecyclerViewMaker(binding: Tytle_Dialig) {
        var tytle: RecyclerView = binding.recyclerView2
        existsProjects.layoutManager = LinearLayoutManager(context)
        adapter = item_exist_project_adapter()
        existsProjects.adapter = adapter
    }
*/
}