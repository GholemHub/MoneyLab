package com.gholemhub.moneylab.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.gholemhub.moneylab.databinding.ActivityAuthenticationBinding
import com.gholemhub.moneylab.model.AppRepository


class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding

    private lateinit var repository: AppRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var v = this

        repository = AppRepository(this)

        binding.GoogleSignInButton.setOnClickListener {
            repository.signInWithGoogle()
        }

    }

}