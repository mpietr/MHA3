package com.example.mha3

import android.graphics.Rect
import android.util.Log
import kotlin.math.pow


class BoardObject(maxX: Int, maxY: Int, factorSize: Float) {

    private var BASESPEED = 6
    private var left = 0
    private var top = 0
    private var size = 0
    private var maxX: Int
    private var maxY: Int
    private var r: Double = 0.0

    private var speedX = 0;
    private var speedY = 0;

    init {
        this.maxX = maxX
        this.maxY = maxY
        this.size = (factorSize*maxX).toInt()
        this.left = 0
        this.top = 0
        this.r = size*0.2
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
        val newX = left + speedX
        val newY = top + speedY
        val yCollision = checkYCollision(newY)
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

    fun checkCollisionWithObject(o: BoardObject): Boolean {
        val r1 = r
        val x1 = (left + size)/2.0
        val y1 = (top + size)/2.0
        val r2 = o.r
        val x2 = (o.left + o.size)/2.0
        val y2 = (o.top + o.size)/2.0

        //(R0 - R1)^2 <= (x0 - x1)^2 + (y0 - y1)^2 <= (R0 + R1)^2

        if((r1-r2).pow(2) < (x1 - x2).pow(2) + (y1 - y2).pow(2)
            && (x1 - x2).pow(2) + (y1 - y2).pow(2) < (r1+r2).pow(2)) {
            return true
        }

        return false
    }

}