package com.example.android.customfancontroller

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import java.util.concurrent.TimeUnit
import kotlin.math.min

class ChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var bgColor = 0
    private var color1 = 0
    private var color2 = 0
    private var color3 = 0

    fun setColor(array: Array<Int>){
        bgColor = array[0]
        color1 = array[1]
        color2 = array[2]
        color3 = array[3]
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint2 = Paint(Paint.ANTI_ALIAS_FLAG)
    private var radius = 0.0f
    private var bgRadius = 0.0f
    private var thin = 5f
    private var stroke = 80f - thin


    private lateinit var rect: RectF
    private var centerX = 0.0f
    private var centerY = 0.0f

    private val goal = arrayOf(220F, 160f, 90f)
    private var percent = arrayOf(0f, 0f, 0f)

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        // Calculate the radius from the smaller of the width and height.
        initAnimation()
    }

    private fun initAnimation(){
        percent = arrayOf(0f, 0f, 0f)

        radius = (min(width, height) / 2.0 * 0.8).toFloat()
        centerX = (width / 2).toFloat()
        centerY = (height / 2).toFloat()
        bgRadius = radius
        stroke = radius/9 * 2 - thin

        paint.apply {
            color = bgColor
            style = Paint.Style.STROKE
            strokeWidth = stroke
        }

        start(0)
        start(1)
        start(2)
    }


    private fun start(num: Int) {

        val mTimerAnimator = ValueAnimator.ofFloat(0F, goal[num])
        mTimerAnimator.duration = 1000
        mTimerAnimator.interpolator = AccelerateDecelerateInterpolator()
        mTimerAnimator.addUpdateListener(AnimatorUpdateListener { animation ->
            drawProgress(
                animation.animatedValue as Float,
                num
            )
        })
        mTimerAnimator.start()
    }

    private fun drawProgress(progress: Float, num: Int) {
        percent[num] = progress
        invalidate()
    }



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        radius = bgRadius
        for(i in 0..2){
            canvas.drawCircle(centerX, centerY, radius, paint)
            radius -= (stroke + thin)
        }

        paint2.apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = stroke
        }

        radius = bgRadius
        rect = RectF(centerX - radius, centerY - radius,centerX + radius,centerY + radius)
        paint2.color = color3
        canvas.drawArc(rect, -90F, percent[0], false, paint2)

        radius -= (stroke + thin)
        rect = RectF(centerX - radius, centerY - radius,centerX + radius,centerY + radius)
        paint2.color = color2
        canvas.drawArc(rect, -90F, percent[1], false, paint2)

        radius -= (stroke + thin)
        rect = RectF(centerX - radius, centerY - radius,centerX + radius,centerY + radius)
        paint2.color = color1
        canvas.drawArc(rect, -90F, percent[2], false, paint2)

    }

}