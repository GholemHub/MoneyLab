package com.gholemhub.moneylab.classes

import androidx.lifecycle.ViewModel

data class Category (var type: Int = 0, var image: Int = 0, var title: String = "")
    : ViewModel()