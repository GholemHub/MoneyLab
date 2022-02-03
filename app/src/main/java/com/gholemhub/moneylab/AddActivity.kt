package com.gholemhub.moneylab

import android.app.Dialog
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import org.mariuszgromada.math.mxparser.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.adapters.AdapterExpense


import com.gholemhub.moneylab.databinding.ActivityAddBinding
import com.gholemhub.moneylab.databinding.DialogTytleBinding
import com.gholemhub.moneylab.viewmodels.AdapterViewModel


class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    companion object {
        @JvmStatic

        lateinit var dialog: Dialog
        var TitleType = mutableListOf<AdapterViewModel>()
        var TitleTypeLine = 0
    }


    private lateinit var bindingDialig: DialogTytleBinding
    private

    lateinit var Adapter1: AdapterExpense

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)


        //Disable keyboard on editText
        binding.inputText.showSoftInputOnFocus = false

        setTitle()

        TitleType.add(AdapterViewModel("123", "1income", "income", 3))
        TitleType.add(AdapterViewModel("123", "2income", "income", 3))
        TitleType.add(AdapterViewModel("123", "3expense", "expense", 1))
        TitleType.add(AdapterViewModel("123", "4expense", "expense", 1))
        TitleType.add(AdapterViewModel("123", "5expense", "expense", 1))
        TitleType.add(AdapterViewModel("123", "6expense", "expense", 1))
        TitleType.add(AdapterViewModel("123", "7expense", "expense", 1))
        TitleType.add(AdapterViewModel("123", "8expense", "expense", 1))
        TitleType.add(AdapterViewModel("123", "9income","income", 3))
        TitleType.add(AdapterViewModel("123", "10expense", "expense", 1))
        TitleType.add(AdapterViewModel("123", "10expense", "expense", 1))
        TitleType.add(AdapterViewModel("123", "15expense", "border", 2))

        TitleType.sortBy { t -> t.id}

        TitleType

        TitleTypeLineFun()
    }

    private fun TitleTypeLineFun() {

        var counter = 0

        TitleType.forEach{f ->
            if(f.type == "expense")
            {
                counter += 1
            }
        }

        if(TitleTypeLine < counter){
            TitleTypeLine = counter
        }

    }

    private fun setTitle() {
        binding.tytleImage.setOnClickListener {

            bindingDialig = DialogTytleBinding.inflate(layoutInflater)
            dialog = Dialog(this)

            dialog.setContentView(bindingDialig.root)

            var tytleIncome: RecyclerView = bindingDialig.recyclerViewIncome
            tytleIncome.layoutManager = LinearLayoutManager(this)
            Adapter1 = AdapterExpense()
            tytleIncome.adapter = Adapter1

            Adapter1.notifyDataSetChanged()

            dialog.show()
        }
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
        updateText("00")
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
