package com.gholemhub.moneylab.model

import android.content.Intent
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.classes.User
import com.gholemhub.moneylab.classes.TitleIE
import com.gholemhub.moneylab.classes.TransactionVM
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
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase

class AppRepository {

    companion object {
        @JvmStatic

        lateinit var userModel: User


    }

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
              d("TAG", "ApiException: $e")
           }
       }
   }


//TODO optimizate lists
    fun GetTitlesFromFirestore(){
        fStore.collection("Users").addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                var Testlist = mutableListOf<User>()
                if(error != null){
                    d("TAG", error.message.toString())
                    return
                }
                //Taking the data from Firestore to class USER
                for(dc : DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                        Testlist.add(dc.document.toObject(User::class.java))

                    }
                }

                //Replase Users titles
                for(i in Testlist){
                    userModel.ListOfTitles.clear()
                    userModel.ListOfTitles = i.ListOfTitles
                    d("TAG", "Title: " + i.idTocken)
                }
            }

        })
    }

    fun GetTransactionFromFirestore(){

        fStore.collection("Users").addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                var Testlist = mutableListOf<User>()
                if(error != null){
                    d("TAG", error.message.toString())
                    return
                }
                //Taking the data from Firestore to class USER
                for(dc : DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                        Testlist.add(dc.document.toObject(User::class.java))
                    }
                }

                //Replase Users transactions
                for(i in Testlist){
                    userModel.ListOfTransactions.clear()
                    userModel.ListOfTransactions = i.ListOfTransactions
                    d("TAG", "Title: " + i.idTocken)
                }
            }

        })
    }

//create question if ID is unic
    private fun firebaseAuthWithGoogle(idTocken: String){
        val credential = GoogleAuthProvider.getCredential(idTocken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                Log.d("TAG", "Google sign in done")


                userId = auth.currentUser!!.uid
                userModel = User(userId)

                //if Yes create the new user in FirestoreDB
                CreateUserOnDB()

            }else{
                d("TAG", "Google sign in error")
            }
        }
    }

    fun chechAuthState(){
        //if user is logged in than skip this active
        if(auth.currentUser != null){
            var intent = Intent(authenticationActivity, MainActivity::class.java)
            //TODO change !! to If statement
            //if(auth.currentUser!!.uid != null)
            userId = auth.currentUser!!.uid
            userModel = User(userId)
            startActivity(authenticationActivity, intent, null)
        }
    }

    fun ThrowTransactionToFirestore(transaction: TransactionVM){
        var documentReference: DocumentReference = fStore.collection("Users").document(userId)

        userModel.ListOfTransactions.add(transaction)

        documentReference.set(userModel).addOnSuccessListener {

            Toast.makeText(authenticationActivity, "Success", Toast.LENGTH_LONG).show()

        }.addOnFailureListener{
            Toast.makeText(authenticationActivity, "Failed", Toast.LENGTH_LONG).show()
        }
    }



    private fun CreateUserOnDB() {

        //Creating users data in FirebaseDB to folder /Users/userId
        var documentReference: DocumentReference = fStore.collection("Users").document(userId)

        //Putting the data to file
        val user = User(account.idToken.toString())

        AddTitles(user)

        documentReference.set(user).addOnSuccessListener {

            Toast.makeText(authenticationActivity, "Success", Toast.LENGTH_LONG).show()

        }.addOnFailureListener{
            Toast.makeText(authenticationActivity, "Failed", Toast.LENGTH_LONG).show()
        }
    }

    private fun AddTitles(user: User) {

        user.ListOfTitles.add(TitleIE(R.drawable.outline_ramen_dining_24, "Food",  1))
        user.ListOfTitles.add(TitleIE(R.drawable.outline_directions_bus_24, "Transport",  1))
        user.ListOfTitles.add(TitleIE(R.drawable.outline_attractions_24, "Fun",  1))
        user.ListOfTitles.add(TitleIE(R.drawable.outline_fitness_center_24, "Sport",  1))
        user.ListOfTitles.add(TitleIE(R.drawable.outline_local_taxi_24, "Taxi",  1))
        user.ListOfTitles.add(TitleIE(R.drawable.outline_medical_services_24, "Medicine",  1))
        user.ListOfTitles.add(TitleIE(R.drawable.outline_school_24, "Education",  1))
        user.ListOfTitles.add(TitleIE(R.drawable.outline_shopping_cart_24, "Shopping",  1))
        user.ListOfTitles.add(TitleIE(R.drawable.outline_waterfall_chart_24, "Stock",  1))
        user.ListOfTitles.add(TitleIE(R.drawable.outline_sports_bar_24, "Alcohol",  1))
        user.ListOfTitles.add(TitleIE(R.drawable.outline_phone_iphone_24, "Phone bill",  1))
        user.ListOfTitles.add(TitleIE(R.drawable.outline_cottage_24, "Home bill",  1))

        user.ListOfTitles.add(TitleIE(R.drawable.outline_directions_bus_24, "15expense",  2))

        user.ListOfTitles.add(TitleIE(R.drawable.outline_paid_24, "Salary",  3))
        user.ListOfTitles.add(TitleIE(R.drawable.ic_baseline_bar_chart_24, "Percent",3))


        user.ListOfTitles.sortBy { t -> t.id}
/*
        TitleType.add(TitleIE(R.drawable.outline_ramen_dining_24, "Food",  1))
        TitleType.add(TitleIE(R.drawable.outline_directions_bus_24, "Transport",  1))
        TitleType.add(TitleIE(R.drawable.outline_attractions_24, "Fun",  1))
        TitleType.add(TitleIE(R.drawable.outline_fitness_center_24, "Sport",  1))
        TitleType.add(TitleIE(R.drawable.outline_local_taxi_24, "Taxi",  1))
        TitleType.add(TitleIE(R.drawable.outline_medical_services_24, "Medicine",  1))
        TitleType.add(TitleIE(R.drawable.outline_school_24, "Education",  1))
        TitleType.add(TitleIE(R.drawable.outline_shopping_cart_24, "Shopping",  1))
        TitleType.add(TitleIE(R.drawable.outline_waterfall_chart_24, "Stock",  1))
        TitleType.add(TitleIE(R.drawable.outline_sports_bar_24, "Alcohol",  1))
        TitleType.add(TitleIE(R.drawable.outline_phone_iphone_24, "Phone bill",  1))
        TitleType.add(TitleIE(R.drawable.outline_cottage_24, "Home bill",  1))

        TitleType.add(TitleIE(R.drawable.outline_directions_bus_24, "15expense",  2))

        TitleType.add(TitleIE(R.drawable.outline_paid_24, "Salary",  3))
        TitleType.add(TitleIE(R.drawable.ic_baseline_bar_chart_24, "Percent",3))

        TitleType.sortBy { t -> t.id}*/
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