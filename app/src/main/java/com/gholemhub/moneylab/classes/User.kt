package com.gholemhub.moneylab.classes

data class User(val idTocken: String = "") {

    //var ListOfTitles = mutableListOf<TitleIE>()
    var ListOfTransactions = mutableListOf<TransactionVM>()
    var ListOfCategoryes = mutableListOf<Category>()

    var Money = 0
    fun addMoney(count: Int){
        Money += count
    }
    fun minusMoney(count: Int){
        Money = Money - count
    }
    fun showMoney() = Money


}