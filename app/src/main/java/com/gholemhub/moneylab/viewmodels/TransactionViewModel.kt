package com.gholemhub.moneylab.viewmodels

import androidx.lifecycle.ViewModel
import java.util.*

data class TransactionViewModel(var image: Int, var title: String, var id: Int, var count: Int, var date: String): ViewModel() {

}