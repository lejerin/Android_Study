package com.example.android.customfancontroller

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.pie_chart_custom.view.*

open class CustomPieChart @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, defStyleAttr: Int=0)
    : LinearLayout(context, attrs, defStyleAttr) {

    companion object {

    }

    init {
        View.inflate(context, R.layout.pie_chart_custom, this)

        val arr = arrayOf(0,0,0,0)
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.CustomPieChart)
        arr[0] = typedArray.getColor(R.styleable.CustomPieChart_bgColor,0)
        arr[1] = typedArray.getColor(R.styleable.CustomPieChart_color1,0)
        arr[2] = typedArray.getColor(R.styleable.CustomPieChart_color2,0)
        arr[3] = typedArray.getColor(R.styleable.CustomPieChart_color3,0)
        typedArray.recycle()


        chartView.setColor(arr)
    }



}