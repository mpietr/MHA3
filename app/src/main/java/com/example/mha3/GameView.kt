package com.example.mha3

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View


class GameView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    private var bitmap : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.hedgehog)

    private val contentWidth = width - paddingLeft - paddingRight
    private val contentHeight = height - paddingTop - paddingBottom


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(bitmap, null, Rect(100,100,400, 400), null)
    }
}