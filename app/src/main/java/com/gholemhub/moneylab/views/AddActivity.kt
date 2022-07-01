package com.gholemhub.moneylab


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log.d
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.adapters.AdapterAdd
import com.gholemhub.moneylab.databinding.ActivityAddBinding
import com.gholemhub.moneylab.databinding.DialogTytleBinding
import com.gholemhub.moneylab.model.AppRepository.Companion.userModel
import com.gholemhub.moneylab.classes.TitleIE
import com.gholemhub.moneylab.classes.TransactionVM
import com.gholemhub.moneylab.model.AddRepository.Companion.AddContext
import com.gholemhub.moneylab.model.AppRepository
import com.gholemhub.moneylab.model.AppRepository.Companion.activityAddBinding
import com.gholemhub.moneylab.model.AppRepository.Companion.bindingPreAuthentication
import com.gholemhub.moneylab.model.AppRepository.Companion.repository

import com.gholemhub.moneylab.views.FragmentTransaction
import com.gholemhub.moneylab.views.MainActivity
import org.mariuszgromada.math.mxparser.*
import java.text.SimpleDateFormat
import java.util.*


class AddActivity : AppCompatActivity(), AdapterAdd.DialogAddListener {


    private var idOfTipe: Int = 0
    private lateinit var titleIE: TitleIE


    lateinit var adapter1: AdapterAdd



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityAddBinding = ActivityAddBinding.inflate(layoutInflater)

        setContentView(activityAddBinding.root)
        AddContext = this



    }

}
