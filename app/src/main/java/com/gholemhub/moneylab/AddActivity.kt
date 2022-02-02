package com.gholemhub.moneylab

import android.app.Dialog
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import org.mariuszgromada.math.mxparser.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.adapters.AdapterExpense

import com.gholemhub.moneylab.adapters.ExpenseItem
import com.gholemhub.moneylab.databinding.ActivityAddBinding
import com.gholemhub.moneylab.databinding.DialogTytleBinding
import com.gholemhub.moneylab.viewmodels.AdapterViewModel


class AddActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAddBinding

    companion object {
        @JvmStatic


        var arrList = mutableListOf<AdapterViewModel>()
    }


    private lateinit var bindingDialig: DialogTytleBinding
    private lateinit var dialog: Dialog




    lateinit var Adapter1: AdapterExpense

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)




        //Disable keyboard on editText
        binding.inputText.showSoftInputOnFocus = false

        binding.tytleImage.setOnClickListener {

            bindingDialig = DialogTytleBinding.inflate(layoutInflater)
            dialog = Dialog(this)

            //recyclerView = bindingDialig.recyclerViewIncome
            //recyclerView.layoutManager = LinearLayoutManager(this)
            dialog.setContentView(bindingDialig.root)



            var tytleIncome: RecyclerView = bindingDialig.recyclerViewIncome
            tytleIncome.layoutManager = LinearLayoutManager(this)
            Adapter1 = AdapterExpense()
            tytleIncome.adapter = Adapter1

            //var tytleIncome: RecyclerView = bindingDialig.recyclerViewIncome
            //tytleIncome.layoutManager = LinearLayoutManager(this)


            //recyclerView.adapter = Adapter1

            Adapter1.notifyDataSetChanged()

            dialog.show()

            //
            //recyclerView.adapter = Adapter1

/*




            var tytleIncome: RecyclerView = bindingDialig.recyclerViewIncome
            tytleIncome.layoutManager = LinearLayoutManager(this)

            tytleIncome.adapter = adapterIncome

            dialog.show()

*/

        }



        arrList.add(AdapterViewModel("123", "1"))
        arrList.add(AdapterViewModel("123", "2"))
        arrList.add(AdapterViewModel("123", "3"))
        arrList.add(AdapterViewModel("123", "4"))
        arrList.add(AdapterViewModel("123", "5"))
        arrList.add(AdapterViewModel("123", "6"))
        arrList.add(AdapterViewModel("123", "7"))
        arrList.add(AdapterViewModel("123", "8"))
        arrList.add(AdapterViewModel("123", "9"))
        arrList.add(AdapterViewModel("123", "10"))
        arrList.add(AdapterViewModel("123", "10"))


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
