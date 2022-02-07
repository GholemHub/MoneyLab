package com.gholemhub.moneylab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gholemhub.moneylab.databinding.ActivityMainBinding
import com.gholemhub.moneylab.viewmodels.MainActivityViewModel


class MainActivity : AppCompatActivity(), LifecycleOwner{

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        MenuListener()
        var m = ViewModelProvider(this).get(MainActivityViewModel::class.java)

    }

    private fun MenuListener() {
        binding.bnv?.background = null
        binding.bnv?.menu?.getItem(1)?.isEnabled = false

        var addFragment = FragmentTransaction()

        var navigationController = findNavController(R.id.Fragment)
        binding.bnv?.setupWithNavController(navigationController)


        binding.fab?.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddActivity::class.java))
        }


/*
        binding.fab?.setOnClickListener {

            var man = supportFragmentManager
            var df = man.beginTransaction()
            df.replace(R.id.Fragment, FragmentAdd())
            //df.add(R.id.containerFragmentFL, FragmentAdd())
            df.commit()

        }*/


    }
}