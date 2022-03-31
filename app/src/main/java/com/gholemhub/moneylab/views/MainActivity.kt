package com.gholemhub.moneylab.views

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.gholemhub.moneylab.AddActivity
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.databinding.ActivityMainBinding
import com.gholemhub.moneylab.model.AppRepository.Companion.repository
import com.gholemhub.moneylab.viewmodels.MainActivityViewModel


class MainActivity : AppCompatActivity(), LifecycleOwner{

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        MenuListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    //binding.
       //menuInflater.inflate(binding.top_navigation_bar)
    menuInflater.inflate(R.menu.top_navigation_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        var navHostFragment = supportFragmentManager.findFragmentById(R.id.Fragment)
        var navController = navHostFragment?.findNavController()
        d("TAG", "NAVSETS")
        return item.onNavDestinationSelected(navController!!) || super.onOptionsItemSelected(item)
    }

    private fun ReplaseFragment(fragment: Fragment) {
        var fragmentManager = supportFragmentManager
        var transition = fragmentManager.beginTransaction()
        transition.replace(R.id.Fragment, fragment)
        transition.commit()
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