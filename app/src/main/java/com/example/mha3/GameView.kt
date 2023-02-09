package com.example.mha3

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View


class GameView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    private var hBit = BitmapFactory.decodeResource(resources, R.drawable.hedgehog)


    private lateinit var hedgehog: BoardObject


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        hedgehog = BoardObject(widthSize, heightSize, 0.2f)

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hedgehog.move()
        canvas.drawBitmap(hBit, null, hedgehog.getPosition(), null)
    }

    fun setHedgeHogSpeed(x: Float, y: Float) {
        hedgehog.setSpeed(x, y)
    }

}