package com.example.mha3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var startButton : Button
    private lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == 1001) {
                textView.setText("You lost :(")
                startButton.setText("Try again")
            } else if (result.resultCode == 1000) {
                textView.setText("Congrats on winning!")
                startButton.setText("Play again")
            } else {
                textView.setText("Welcome to Mr. Hedgehog's Adventures 3!")
                startButton.setText("Start")
            }
        }

        startButton = findViewById(R.id.startButton)
        startButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            resultLauncher.launch(intent)
        }

    }

}