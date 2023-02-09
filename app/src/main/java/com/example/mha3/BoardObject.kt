package com.example.mha3

import android.graphics.Rect
import android.util.Log



class BoardObject(maxX: Int, maxY: Int, factorSize: Float) {

    private var BASESPEED = 5
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

    private fun checkXCollision(x : Int): Boolean {
        if (x < 0) {
            return true
        }
        if (x + size > maxX) {
            return true
        }
        return false
    }

    private fun checkYCollision(y: Int) : Boolean {
        if (y < 0) {
            return true
        }
        if (y + size > maxY) {
            return true
        }
        return false
    }

    fun move() {
        var newX = left + speedX
        var newY = top + speedY
        var yCollision = checkYCollision(newY)
        if (checkXCollision(newX)) {
            if (yCollision) {
                return
            }
            top = newY
        } else {
            if (yCollision) {
                left = newX
            } else {
                left = newX
                top = newY
            }
        }

    }

    fun setSpeed(x: Float, y: Float) {
        speedX = BASESPEED*x.toInt()
        speedY = BASESPEED*y.toInt()
    }

    fun getPosition() : Rect {
        return Rect(left, top, left + size, top + size)
    }

}