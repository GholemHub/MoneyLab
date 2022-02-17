package com.gholemhub.moneylab.classes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.gholemhub.moneylab.model.AppRepository.Companion.userModel

import com.gholemhub.moneylab.views.MainActivity


class ItemSelectionDecoration: RecyclerView.ItemDecoration( ) {

    private var list = mutableListOf<View>()
    private var list2 = mutableListOf<Rect>()
    private var list3 = mutableListOf<cl>()

    class cl(val view: View,val  outRect: Rect)



    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {


        d("TAG", "TEST ITEMOFSET")
        val itemCount = state.itemCount

        val itemPosition: Int = parent.getChildAdapterPosition(view)

        // no position, leave it alone
        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }
        CheckIfEquel(itemPosition, view, outRect)
/*
        // first item
        if (itemPosition == 0) {
            d("TAG", "Test of getItenOffsets 1 ")

            CheckIfEquel(itemPosition, view, outRect)

            //view.setBackgroundColor(Color.YELLOW)
            //outRect.set(50, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom())
        }
        // last item
        else if (itemCount > 0 && itemPosition == itemCount - 1) {
            d("TAG", "Test of getItenOffsets 2")
            CheckIfEquel(itemPosition, view, outRect)

            //CheckIfEquel(itemPosition, view)
            //view.setBackgroundColor(Color.MAGENTA)

        }

        // every other item
        else {
            CheckIfEquel(itemPosition, view, outRect)

            d("TAG", "Test of getItenOffsets 3")
            //CheckIfEquel(itemPosition, view)
            //view.setBackgroundColor(Color.BLUE)
            //outRect.set(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom())
        }
*/
    }

    private fun CheckIfEquel(itemPosition: Int, view: View, outRect: Rect) {

        if(userModel.ListOfTransactions[itemPosition].date == "2022/02/15"){

            list.add(view)
            list2.add(outRect)
            list3.add(cl(view, outRect))
            outRect.top = 200
            //outRect.set(view.getPaddingLeft(), 200,   view.getPaddingRight(),  view.getPaddingBottom())
/*
            d("TAG", "DATE 15: ${userModel.ListOfTransactions[itemPosition].date}")
            view.setBackgroundColor(Color.BLUE)
            outRect.set(view.getPaddingLeft(), 200, view.getPaddingRight(), view.getPaddingBottom())
*/
        }else{

            view.setBackgroundColor(Color.YELLOW)
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

       // d("TAG","SIZE: " + list3[0].outRect.toString() )



        for(i in 0..list3.size-1){
            list3[i].view.setBackgroundColor(Color.BLUE)
            list3[0].outRect.top = 200

            //list2[i].set(list3[i].view.getPaddingLeft(), 200,   list3[i].view.getPaddingRight(),   list3[i].view.getPaddingBottom())
            //outRect.set(  list[0].getPaddingLeft(), 200,   list[0].getPaddingRight(),   list[0].getPaddingBottom())
            d("TAG","SIZE: " + list2[0].toString())
        }

    }
}