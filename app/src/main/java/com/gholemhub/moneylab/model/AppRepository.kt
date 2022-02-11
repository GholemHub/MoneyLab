package com.gholemhub.moneylab.model

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.classes.User
import com.gholemhub.moneylab.views.AuthenticationActivity
import com.gholemhub.moneylab.views.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class AppRepository {
    //private lateinit var launcher: ActivityResultLauncher<Intent>
    private var auth: FirebaseAuth


    private lateinit var account: GoogleSignInAccount
    private var launcher: ActivityResultLauncher<Intent>

    private var fStore: FirebaseFirestore
    private lateinit var userId: String

    private var authenticationActivity: AuthenticationActivity

   constructor( i: AuthenticationActivity){
       this.authenticationActivity = i
       this.auth = Firebase.auth
       this.fStore = FirebaseFirestore.getInstance()


       launcher = authenticationActivity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
           val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
           try {
               account = task.getResult(ApiException::class.java)

               if(account != null){
                   firebaseAuthWithGoogle(account.idToken!!)
               }
           }catch (e: ApiException){
               Log.d("TAG", "ApiException: $e")
           }
       }
   }

    private fun firebaseAuthWithGoogle(idTocken: String){
        val credential = GoogleAuthProvider.getCredential(idTocken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                Log.d("TAG", "Google sign in done")

                userId = auth.currentUser!!.uid

                CreateUserOnDB()
                chechAuthState()
            }else{
                Log.d("TAG", "Google sign in error")
            }
        }
    }

    fun chechAuthState(){
        if(auth.currentUser != null){
            //val myIntent = Intent("my.action.bat.schedule_act")
            var intent = Intent(authenticationActivity, MainActivity::class.java)
            startActivity(authenticationActivity, intent, null)
        }
    }

    private fun CreateUserOnDB() {

        var documentReference: DocumentReference = fStore.collection("Users").document(userId)
        val user = User(account.idToken.toString())
        documentReference.set(user).addOnSuccessListener {

            Toast.makeText(authenticationActivity, "Success", Toast.LENGTH_LONG).show()

        }.addOnFailureListener{
            Toast.makeText(authenticationActivity, "Failed", Toast.LENGTH_LONG).show()
        }
    }

    private fun getClient(): GoogleSignInClient {

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(authenticationActivity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(authenticationActivity, gso)
    }

    fun signInWithGoogle(){
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }
}