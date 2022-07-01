package com.gholemhub.moneylab.model

import com.gholemhub.moneylab.AddActivity
import com.gholemhub.moneylab.R
import com.gholemhub.moneylab.classes.Category
import com.gholemhub.moneylab.classes.CategoryImage
import com.gholemhub.moneylab.classes.TransactionVM
import com.google.firebase.firestore.FirebaseFirestore

class AddRepository {
    companion object {
        @JvmStatic
        lateinit var AddContext: AddActivity
        var addNewCategory: Category = Category()
        val ImagesCategoryList: MutableList<CategoryImage> =
            mutableListOf(
                CategoryImage(1, R.drawable.outline_shopping_cart_24),
                CategoryImage(2, R.drawable.outline_cottage_24),
                CategoryImage(3, R.drawable.outline_phone_iphone_24),
                CategoryImage(4, R.drawable.outline_medical_services_24))

    }

    private var fStore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var userId: String

    fun PushTransaction(transactionVM: TransactionVM){
       /* var documentReference: DocumentReference = fStore.collection("Users").document(userId)

        //Putting the data to file
        val user = User(account.idToken.toString())

        AddTitles(user)

        Toast.makeText(AppRepository.activityMain, "CreateUserOnDB", Toast.LENGTH_LONG).show()
        Log.d("TAG", "CreateUserOnDB")

        documentReference.set(user).addOnSuccessListener {

            Toast.makeText(AppRepository.activityMain, "Success", Toast.LENGTH_LONG).show()

        }.addOnFailureListener{
            Toast.makeText(AppRepository.activityMain, "Failed", Toast.LENGTH_LONG).show()
        }
*/
    }





}