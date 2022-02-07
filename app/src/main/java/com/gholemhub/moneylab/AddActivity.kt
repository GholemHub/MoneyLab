package com.gholemhub.moneylab

import android.app.Dialog
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log.d
import android.view.View
import android.widget.Toast
import org.mariuszgromada.math.mxparser.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.adapters.AdapterAddDialog


import com.gholemhub.moneylab.databinding.ActivityAddBinding
import com.gholemhub.moneylab.databinding.DialogTytleBinding
import com.gholemhub.moneylab.viewmodels.AddViewModel
import kotlin.properties.Delegates


class AddActivity : AppCompatActivity(), AdapterAddDialog.DialogAddListener {


    private var idOfTipe: Int = 0


    companion object {
        @JvmStatic

        lateinit var dialog: Dialog
        var TitleType = mutableListOf<AddViewModel>()
        var TitleTypeLine = 0
        lateinit var binding: ActivityAddBinding
    }


    private lateinit var bindingDialig: DialogTytleBinding

    lateinit var adapter1: AdapterAddDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)


        //Disable keyboard on editText
        binding.inputText.showSoftInputOnFocus = false

        TitleType.add(AddViewModel(R.drawable.outline_directions_bus_24, "1income", "income", 1))
        TitleType.add(AddViewModel(R.drawable.outline_directions_bus_24, "2income", "expense", 3))
        TitleType.add(AddViewModel(R.drawable.outline_phone_iphone_24, "4expense", "expense", 1))
        TitleType.add(AddViewModel(R.drawable.outline_phone_iphone_24, "5expense", "expense", 1))
        TitleType.add(AddViewModel(R.drawable.outline_phone_iphone_24, "6expense", "expense", 1))
        TitleType.add(AddViewModel(R.drawable.outline_phone_iphone_24, "3expense", "expense", 1))
        TitleType.add(AddViewModel(R.drawable.outline_phone_iphone_24, "7expense", "expense", 1))
        TitleType.add(AddViewModel(R.drawable.outline_phone_iphone_24, "8expense", "expense", 1))
        TitleType.add(AddViewModel(R.drawable.outline_ramen_dining_24, "9income","income", 3))
        TitleType.add(AddViewModel(R.drawable.outline_ramen_dining_24, "10expense", "expense", 1))
        TitleType.add(AddViewModel(R.drawable.outline_ramen_dining_24, "10expense", "expense", 1))
        TitleType.add(AddViewModel(R.drawable.outline_directions_bus_24, "15expense", "border", 2))

        TitleType.sortBy { t -> t.id}

        setTitle()
        //setImageListener(binding)
    }

    fun setImageListener(binding: ActivityAddBinding) {

        binding.tytleImage.setImageResource(TitleType[idOfTipe].image)
    }

    override fun applyTipe(id: Int) {
        d("TAG", "id: " + id)
        idOfTipe = id
    }

    private fun setTitle() {
        binding.tytleImage.setOnClickListener {

            bindingDialig = DialogTytleBinding.inflate(layoutInflater)
            dialog = Dialog(this)

            dialog.setContentView(bindingDialig.root)

            var tytleIncome: RecyclerView = bindingDialig.recyclerViewIncome
            tytleIncome.layoutManager = LinearLayoutManager(this)
            adapter1 = AdapterAddDialog()
            tytleIncome.adapter = adapter1

            adapter1.notifyDataSetChanged()

            dialog.show()


        }

        //d("TAG", "id2: " + idOfTipe)

        //setImageListener(binding)
    }
    private fun updateText(newStr: String){
    var oldStr = binding.inputText.text.toString()
    var coursorPos = binding.inputText.selectionStart
    var leftStr = oldStr.substring(0, coursorPos)
    var rightStr = oldStr.substring(coursorPos)
    binding.inputText.setText(String.format("%s%s%s", leftStr, newStr, rightStr))
    binding.inputText.setSelection(coursorPos+1)
}
    fun btnListener_zero(View: View){
        updateText("0")
    }
    fun btnListener_zero_zero(View: View){

        var oldStr = binding.inputText.text.toString()
        var coursorPos = binding.inputText.selectionStart
        var leftStr = oldStr.substring(0, coursorPos)
        var rightStr = oldStr.substring(coursorPos)
        binding.inputText.setText(String.format("%s%s%s", leftStr, "00", rightStr))
        binding.inputText.setSelection(coursorPos+2)

        //updateText("00")
    }
    fun btnListener_one(View: View){
        updateText("1")
    }
    fun btnListener_two(View: View){
        updateText("2")
    }
    fun btnListener_three(View: View){
        updateText("3")
    }
    fun btnListener_four(View: View){
        updateText("4")
    }
    fun btnListener_five(View: View){
        updateText("5")
    }
    fun btnListener_six(View: View){
        updateText("6")
    }
    fun btnListener_seven(View: View){
        updateText("7")
    }
    fun btnListener_eight(View: View){
        updateText("8")
    }
    fun btnListener_nine(View: View){
        updateText("9")
    }
    fun btnListener_clear(View: View){
        binding.inputText.setText("")
    }
    fun btnListener_Plus(View: View){
        updateText("+")
    }
    fun btnListener_Minuse(View: View){
        updateText("-")
    }
    fun btnListener_Multiplication(View: View){
        updateText("×")
    }
    fun btnListener_Division(View: View){
        updateText("/")
    }
    fun btnListener_Dot(View: View){
        updateText(".")
    }
    fun btnListener_Equel(View: View){
        var str = binding.inputText.text.toString()

        str = str.replace("×", "*")

        var exp: Expression = Expression(str)

        var res = exp.calculate().toString()
        binding.inputText.setText(res)
        binding.inputText.setSelection(res.length)

    }
    fun btnListener_backspace(View: View){

        var cursorPos = binding.inputText.selectionStart
        var textLen = binding.inputText.text.length

        if(cursorPos != 0 && textLen != 0){

            val selection = binding.inputText.text as SpannableStringBuilder
            selection.replace(cursorPos - 1, cursorPos, "")
            binding.inputText.setText(selection)
            binding.inputText.setSelection(cursorPos - 1)
        }
    }
}
