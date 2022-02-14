package com.gholemhub.moneylab


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log.d
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.adapters.AdapterAddDialog
import com.gholemhub.moneylab.databinding.ActivityAddBinding
import com.gholemhub.moneylab.databinding.DialogTytleBinding
import com.gholemhub.moneylab.model.AppRepository.Companion.userModel
import com.gholemhub.moneylab.classes.TitleIE
import com.gholemhub.moneylab.classes.TransactionVM
import com.gholemhub.moneylab.views.AuthenticationActivity.Companion.repository
import com.gholemhub.moneylab.views.FragmentTransaction
import com.gholemhub.moneylab.views.MainActivity
import org.mariuszgromada.math.mxparser.*
import java.text.SimpleDateFormat
import java.util.*


class AddActivity : AppCompatActivity(), AdapterAddDialog.DialogAddListener {


    private var idOfTipe: Int = 0
    private lateinit var titleIE: TitleIE

    companion object {
        @JvmStatic

        lateinit var dialog: Dialog
        //var TitleType = mutableListOf<TitleIE>()

        lateinit var binding: ActivityAddBinding
    }

    private lateinit var bindingDialig: DialogTytleBinding

    lateinit var adapter1: AdapterAddDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        d("TAG", "ID: " + userModel.idTocken)

        //Disable keyboard on editText
        binding.inputText.showSoftInputOnFocus = false


        CreateDialog()

    }

    override fun applyTipe(item: TitleIE) {
        d("TAG", "id: " + item.id)
        idOfTipe = item.id
        titleIE = item
    }

    private fun CreateDialog() {
        binding.tytleImage.setOnClickListener {

            bindingDialig = DialogTytleBinding.inflate(layoutInflater)
            dialog = Dialog(this)

            dialog.setContentView(bindingDialig.root)

            var tytleIncome: RecyclerView = bindingDialig.recyclerViewIncome
            tytleIncome.layoutManager = LinearLayoutManager(this)
            adapter1 = AdapterAddDialog()
            tytleIncome.adapter = adapter1

            adapter1.notifyDataSetChanged()
            d("TAG", "ID: " + userModel.idTocken)
            repository.GetTitlesFromFirestore()
            dialog.show()
        }

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

                var newT = TransactionVM(titleIE.image, titleIE.title, titleIE.id, expression, CreateDate())
            //FragmentTransaction.TransactionList.add(newT)

            repository.ThrowTransactionToFirestore(newT)

            ChangeActivity()
        }else{
            val myToast = Toast.makeText(this,"Set title!",Toast.LENGTH_SHORT)
            myToast.setGravity(Gravity.END,200,200)
            myToast.show()
        }

    }

    private fun ChangeActivity() {
        var intent = Intent(this, MainActivity::class.java)

        ContextCompat.startActivity(this, intent, null)
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
