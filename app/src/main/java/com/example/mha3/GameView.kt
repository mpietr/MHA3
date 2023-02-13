package com.example.mha3

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View


class GameView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    private var hBit = BitmapFactory.decodeResource(resources, R.drawable.hedgehog)
    private var tBit = BitmapFactory.decodeResource(resources, R.drawable.tomato)


    private lateinit var hedgehog: BoardObject
    private lateinit var tomato: BoardObject


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        hedgehog = BoardObject(widthSize, heightSize, 0.2f)
        tomato = BoardObject(widthSize, heightSize, 0.15f)
        tomato.randomPosition()

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hedgehog.move()
        canvas.drawBitmap(tBit, null, tomato.getPosition(), null)
        canvas.drawBitmap(hBit, null, hedgehog.getPosition(), null)
    }

    fun setHedgeHogSpeed(x: Float, y: Float) {
        hedgehog.setSpeed(x, y)
    }

    fun checkScoreAndChangePosition(): Boolean {
        if (hedgehog.checkCollisionWithObject(tomato)) {
            tomato.randomPosition()
            return true
        }
        return false
    }

}