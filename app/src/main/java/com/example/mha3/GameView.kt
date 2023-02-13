package com.example.mha3

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View


class GameView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    private var hBit = BitmapFactory.decodeResource(resources, R.drawable.hedgehog)
    private var tBit = BitmapFactory.decodeResource(resources, R.drawable.tomato)
    private var sBit = BitmapFactory.decodeResource(resources, R.drawable.stone)


    private lateinit var hedgehog: BoardObject
    private lateinit var tomato: BoardObject
    private var stones = mutableListOf<BoardObject>()
    private var widthSize = 0
    private var heightSize = 0


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        widthSize = MeasureSpec.getSize(widthMeasureSpec)
        heightSize = MeasureSpec.getSize(heightMeasureSpec)
        hedgehog = BoardObject(widthSize, heightSize, 0.2f)
        tomato = BoardObject(widthSize, heightSize, 0.15f)
        tomato.randomPosition()

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hedgehog.move()
        canvas.drawBitmap(tBit, null, tomato.getPosition(), null)
        for (stone in stones) {
            canvas.drawBitmap(sBit, null, stone.getPosition(), null)
        }
        canvas.drawBitmap(hBit, null, hedgehog.getPosition(), null)
    }

    fun setHedgeHogSpeed(x: Float, y: Float) {
        hedgehog.setSpeed(x, y)
    }

    //better tomato positions (split board into grid?)
    fun checkScoreAndChangePosition(): Boolean {
        if (hedgehog.checkCollisionWithObject(tomato)) {
            tomato.randomPosition()
            return true
        }
        return false
    }

    fun checkEnd(): Boolean {
        for (stone in stones) {
            if (hedgehog.checkCollisionWithObject(stone)) {
                return true
            }
        }
        return false
    }

    fun addStone() {
        val s = BoardObject(widthSize, heightSize, 0.2f)
        s.randomPosition()
        while (hedgehog.checkCollisionWithObject(s)) {
            s.randomPosition()
        }
        stones.add(s)
    }

}