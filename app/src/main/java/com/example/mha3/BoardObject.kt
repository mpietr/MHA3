package com.example.mha3

import android.graphics.Rect

class BoardObject(maxX: Int, maxY: Int, factorSize: Float) {

    private var BASESPEED = 1
    private var left = 0
    private var top = 0
    private var size = 0
    private var maxX: Int
    private var maxY: Int

    private var speedX = 0;
    private var speedY = 0;

    init {
        this.maxX = maxX
        this.maxY = maxY
        this.size = (factorSize*maxX).toInt()
        left = 0
        top = 0

    }

    fun randomPosition() {
        left = (0..(maxX-size)).random()
        top = (0..(maxY-size)).random()
    }

    private fun checkCollision(x : Int, y: Int): Boolean {
        if (x + left > maxX) {
            return true
        }
        if (y + top > maxY) {
            return true
        }
        return false
    }

    fun move() {
        var newX = left + speedX
        var newY = top + speedY
        if(checkCollision(newX, newY)) {
            return
        }
        left = newX
        top = newY
    }

    fun setSpeedDirection(x: Int, y: Int) {
        speedX = BASESPEED*x
        speedY = BASESPEED*y
    }

    fun getPosition() : Rect {
        return Rect(left, top, size, size)
    }

}