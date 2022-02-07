package com.gholemhub.moneylab.viewmodels

import androidx.lifecycle.ViewModel

data class TransactionViewModel(var image: Int, var title: String, var type: String, var id: Int, var count: Int): ViewModel() {

}