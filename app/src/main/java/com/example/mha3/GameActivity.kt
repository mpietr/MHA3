package com.example.mha3

import android.content.Intent
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener

class GameActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager : SensorManager
    private lateinit var gameView : GameView
    private lateinit var textView: TextView
    private var points = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        gameView = findViewById(R.id.gameView)
        setTitle("Points: 0")
        setupSensor()
    }

    private fun setupSensor() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
            sensorManager.registerListener(
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_GAME,
                SensorManager.SENSOR_DELAY_GAME
            )
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            //left(10) right (-10)
            val horizontal = event.values[0]
            // up(10), down (-10)
            val vertical = event.values[1]
            //textView.text = "up/down ${horizontal.toInt()}\nleft/right ${vertical.toInt()}"
            gameView.setHedgeHogSpeed(-1*horizontal, vertical)
            if(gameView.checkScoreAndChangePosition()){
                points++
                setTitle("Points: $points")
                if (points%3 == 0) {
                    gameView.addStone()
                }
            }
            if (gameView.checkEnd()) {
                points = 0
                var intent = Intent()
                setResult(1001, intent)
                finish()
            }
            gameView.invalidate()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        super.onDestroy()
    }
}