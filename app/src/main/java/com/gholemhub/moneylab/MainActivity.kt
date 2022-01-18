package com.gholemhub.moneylab

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

        var j = 0

/*
        binding.text.setOnClickListener {
            m.i += 1
            j += 1
            binding.text.setText("${m.i} :: $j")
        }

*/
    }

    private fun MenuListener() {
        binding.bnv?.background = null
        binding.bnv?.menu?.getItem(1)?.isEnabled = false
/*
        binding.fab?.setOnClickListener {
            binding.bnv?.setupWithNavController(navigationContoller)
        }
*/
        var navigationContoller = findNavController(R.id.containerFragment)

        binding.bnv?.setupWithNavController(navigationContoller)

    }
}