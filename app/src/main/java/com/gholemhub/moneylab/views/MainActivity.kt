package com.gholemhub.moneylab.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gholemhub.moneylab.AddActivity
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.databinding.ActivityMainBinding
import com.gholemhub.moneylab.viewmodels.MainActivityViewModel
import com.gholemhub.moneylab.views.AuthenticationActivity.Companion.repository

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    //binding.
       //menuInflater.inflate(binding.top_navigation_bar)
    menuInflater.inflate(R.menu.top_navigation_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
           // R.id.fragmentHome ->
            R.id.fragmentLogout -> repository.signOuthFromGoogle(this)



        }




        return super.onOptionsItemSelected(item)
    }

    private fun MenuListener() {
        binding.bnv?.background = null
        //binding.bnv?.menu?.getItem(1)?.isEnabled = false

            //var addFragment = FragmentTransaction()




        var navigationController = findNavController(R.id.Fragment)
        binding.bnv?.setupWithNavController(navigationController)


        binding.fab?.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddActivity::class.java))
        }
    }
}