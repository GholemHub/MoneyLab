package com.gholemhub.moneylab.classes

import androidx.lifecycle.ViewModel
import java.util.*

data class TransactionVM(var image: Int, var title: String, var id: Int, var count: Int, var date: String): ViewModel() {

}