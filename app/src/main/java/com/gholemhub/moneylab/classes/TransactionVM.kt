package com.gholemhub.moneylab.classes

import androidx.lifecycle.ViewModel
import java.util.*

data class TransactionVM(var image: Int = 0, var title: String = "",
                         var id: Int = 0, var count: Int = 0,
                         var date: String = ""): ViewModel() {

}