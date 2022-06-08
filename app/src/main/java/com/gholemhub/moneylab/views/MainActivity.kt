package com.gholemhub.moneylab.views

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gholemhub.moneylab.AddActivity
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.databinding.ActivityMainBinding
import com.gholemhub.moneylab.model.AppRepository
import com.gholemhub.moneylab.model.AppRepository.Companion.activityMain
import com.gholemhub.moneylab.model.AppRepository.Companion.repository
import com.google.android.material.internal.ContextUtils.getActivity


class MainActivity : AppCompatActivity(), LifecycleOwner{

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        repository = AppRepository(this)

        MenuListener()
    }
/// TODO Set to TOP BAR NAVIGATION
      /*  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //binding.
           //menuInflater.inflate(binding.top_navigation_bar)
        menuInflater.inflate(R.menu.top_navigation_bar, menu)
            return true
        }*/

        /*override fun onOptionsItemSelected(item: MenuItem): Boolean {


            var navHostFragment = supportFragmentManager.findFragmentById(R.id.Fragment)
            var navController = navHostFragment?.findNavController()
            d("TAG", "NAVSETS")



            return item.onNavDestinationSelected(navController!!) || super.onOptionsItemSelected(item)
        }*/

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

            d("TAG", "NAV " + navigationController.currentBackStackEntry)


            //Navigation.findNavController(AppRepository.bindingFragmentChart.root)
            //    .navigate(R.id.action_fragmentChart_to_addFragment)

            startActivity(Intent(this@MainActivity, AddActivity::class.java))
        }
    }
}