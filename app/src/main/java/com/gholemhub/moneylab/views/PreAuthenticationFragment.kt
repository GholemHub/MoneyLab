package com.gholemhub.moneylab.views

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.navigation.Navigation
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.databinding.FragmentPreAuthenticationBinding
import com.gholemhub.moneylab.model.AppRepository
import com.gholemhub.moneylab.model.AppRepository.Companion.auth
import com.gholemhub.moneylab.model.AppRepository.Companion.bindingPreAuthentication
import com.gholemhub.moneylab.model.AppRepository.Companion.repository

class PreAuthenticationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingPreAuthentication = DataBindingUtil.inflate(
            inflater, R.layout.fragment_pre_authentication, container, false)

        //repository = AppRepository()

        Handler().postDelayed({
            if (auth.currentUser != null) {
                Log.d("TAG", "Sign in")
                repository.signInWithGoogle()

                /*Navigation.findNavController(bindingPreAuthentication.root)
                    .navigate(R.id.action_preAuthenticationFragment_to_authenticationFragment)*/

                /*Navigation.findNavController(binding.root)
                    .navigate(R.id.action_preAuthenticationFragment_to_fragmentTransaction2)*/
            } else {
                Log.d("TAG", "Sign up")
                repository.signInWithGoogle()
                /*Navigation.findNavController(bindingPreAuthentication.root)
                    .navigate(R.id.action_preAuthenticationFragment_to_authenticationFragment)*/
            }
        }, 1500)
        return bindingPreAuthentication.root
    }
}