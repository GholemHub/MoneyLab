package com.gholemhub.moneylab


import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log.d
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.adapters.AdapterAddDialog
import com.gholemhub.moneylab.databinding.ActivityAddBinding
import com.gholemhub.moneylab.databinding.DialogTytleBinding
import com.gholemhub.moneylab.viewmodels.AddViewModel
import com.gholemhub.moneylab.viewmodels.TransactionViewModel
import org.mariuszgromada.math.mxparser.*
import java.lang.Exception
import java.lang.Integer.parseInt
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


class AddActivity : AppCompatActivity(), AdapterAddDialog.DialogAddListener {


    private var idOfTipe: Int = 0
    private lateinit var itemAddVM: AddViewModel


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


        TitleType.add(AddViewModel(R.drawable.outline_ramen_dining_24, "Food",  1))
        TitleType.add(AddViewModel(R.drawable.outline_directions_bus_24, "Transport",  1))
        TitleType.add(AddViewModel(R.drawable.outline_attractions_24, "Fun",  1))
        TitleType.add(AddViewModel(R.drawable.outline_fitness_center_24, "Sport",  1))
        TitleType.add(AddViewModel(R.drawable.outline_local_taxi_24, "Taxi",  1))
        TitleType.add(AddViewModel(R.drawable.outline_medical_services_24, "Medicine",  1))
        TitleType.add(AddViewModel(R.drawable.outline_school_24, "Education",  1))
        TitleType.add(AddViewModel(R.drawable.outline_shopping_cart_24, "Shopping",  1))
        TitleType.add(AddViewModel(R.drawable.outline_waterfall_chart_24, "Stock",  1))
        TitleType.add(AddViewModel(R.drawable.outline_sports_bar_24, "Alcohol",  1))
        TitleType.add(AddViewModel(R.drawable.outline_phone_iphone_24, "Phone bill",  1))
        TitleType.add(AddViewModel(R.drawable.outline_cottage_24, "Home bill",  1))

        TitleType.add(AddViewModel(R.drawable.outline_directions_bus_24, "15expense",  2))

        TitleType.add(AddViewModel(R.drawable.outline_paid_24, "Salary",  3))
        TitleType.add(AddViewModel(R.drawable.ic_baseline_bar_chart_24, "Percent",3))

        TitleType.sortBy { t -> t.id}

        setTitle()
        //setImageListener(binding)
    }

    fun setImageListener(binding: ActivityAddBinding) {

        binding.tytleImage.setImageResource(TitleType[idOfTipe].image)
    }

    override fun applyTipe(item: AddViewModel) {
        d("TAG", "id: " + item.id)
        idOfTipe = item.id
        itemAddVM = item
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

    private var boolEquel = false

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
        ChanheColour("#32A852")
    }
    fun btnListener_Plus(View: View){
        updateText("+")
        boolEquel = true
        ChanheColour("#FFC107")

    }
    fun btnListener_Minuse(View: View){
        updateText("-")
        boolEquel = true
        ChanheColour("#FFC107")
    }
    fun btnListener_Multiplication(View: View){
        updateText("×")
        boolEquel = true
        ChanheColour("#FFC107")
    }
    fun btnListener_Division(View: View){
        updateText("/")
        boolEquel = true
        ChanheColour("#FFC107")
    }
    fun btnListener_Dot(View: View){
        updateText(".")
    }

    var expression = 0

    @SuppressLint("ResourceAsColor")
    fun btnListener_Equel(View: View){
        var str = binding.inputText.text.toString()

        str = str.replace("×", "*")
        var exp = Expression(str)
        var res = exp.calculate().toString()

        expression = res.toDouble().toInt()

        binding.inputText.setText(expression.toString())
        binding.inputText.setSelection(expression.toString().length)

        if(boolEquel) {
            ChanheColour("#32A852")
            boolEquel = false
            d("TAG", "ERROR1")
        }
        else{

            CreateTransaction()
        }



    }
    private fun CreateTransaction() {

        if(binding.tytleImage.drawable.toString() !=  "android.graphics.drawable.VectorDrawable@ffe30f7"){

            CreateDate()

                var newT = TransactionViewModel(itemAddVM.image, itemAddVM.title, itemAddVM.id, expression, CreateDate())
            FragmentTransaction.TransactionList.add(newT)



        }else{

            //d("TAG", "ERROR444")
            val myToast = Toast.makeText(this,"Set title!",Toast.LENGTH_SHORT)
            myToast.setGravity(Gravity.END,200,200)
            myToast.show()

        }
        //FragmentTransaction.TransactionList.add(TransactionViewModel(R.drawable.outline_directions_bus_24, "1income", 1, 24))
    }

    private fun CreateDate(): String {
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        val date = Date()
        return sdf.format(date)
    }

    @SuppressLint("ResourceAsColor")
    fun ChanheColour(str: String){
        binding.button22.setBackgroundColor(Color.parseColor(str))
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
