package com.gholemhub.moneylab.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.core.content.ContextCompat
import com.gholemhub.moneylab.classes.User

import com.gholemhub.moneylab.databinding.ActivityAuthenticationBinding
import com.gholemhub.moneylab.model.AppRepository
import com.gholemhub.moneylab.model.AppRepository.Companion.repository


class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding

    /*companion object {
        @JvmStatic


        lateinit var repository: AppRepository

    }*/

    //private lateinit var repository: AppRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = AppRepository(this)

        //repository.chechAuthState()

        binding.GoogleSignInButton.setOnClickListener {
            repository.signInWithGoogle(this)

                //repository.chechAuthState()

        }


    }


}