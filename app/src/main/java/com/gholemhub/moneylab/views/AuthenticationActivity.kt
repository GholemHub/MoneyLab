package com.gholemhub.moneylab.views

import android.content.Intent
import android.os.BaseBundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.classes.User
import com.gholemhub.moneylab.databinding.ActivityAuthenticationBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var dataBase: DatabaseReference

    private lateinit var account: GoogleSignInAccount

    private lateinit var fStore: FirebaseFirestore
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        fStore = FirebaseFirestore.getInstance()

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                account = task.getResult(ApiException::class.java)

                if(account != null){
                    firebaseAuthWithGoogle(account.idToken!!)
                }
            }catch (e: ApiException){
                d("TAG", "ApiException: $e")
            }
        }

        chechAuthState()

        binding.GoogleSignInButton.setOnClickListener {
            signInWithGoogle()
        }


    }

    private fun getClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(this, gso)
    }

    private fun signInWithGoogle(){
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idTocken: String){
        val credential = GoogleAuthProvider.getCredential(idTocken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                d("TAG", "Google sign in done")

                userId = auth.currentUser!!.uid

                CreateUserOnDB()
                chechAuthState()
            }else{
                d("TAG", "Google sign in error")
            }
        }
    }

    private fun CreateUserOnDB() {

        var documentReference: DocumentReference = fStore.collection("Users").document(userId)
        val user = User(account.idToken.toString())
        documentReference.set(user).addOnSuccessListener {

            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()

        }.addOnFailureListener{
            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
        }
        //dataBase = FirebaseDatabase.getInstance().getReference("Users")
/*
        dataBase.child(account.idToken.toString()).setValue(user).addOnSuccessListener {

            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()

        }.addOnFailureListener{
            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
        }*/
    }

    private fun chechAuthState(){
        if(auth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}