package com.gholemhub.moneylab

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDialogFragment

class Tytle_Dialig: AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        

        var builder = AlertDialog.Builder(activity)

        var inflater = activity?.layoutInflater
        var view = inflater?.inflate(R.layout.dialog_tytle, null)

        builder.setView(view)
            .setTitle("Choose tytle")
            .setPositiveButton("Done") { dialog, which ->

            }


        return builder.create()
    }
}