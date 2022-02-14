package com.gholemhub.moneylab.classes

data class User(val idTocken: String = "") {

    var ListOfTitles = mutableListOf<TitleIE>()
    var ListOfTransactions = mutableListOf<TransactionVM>()

}