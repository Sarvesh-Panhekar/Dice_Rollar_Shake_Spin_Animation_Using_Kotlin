package com.example.dicerollar

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var imgDice: ImageView
    private val diceImages = mutableListOf<Int>()
    private lateinit var animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgDice = findViewById(R.id.imgDice)

        val player = MediaPlayer.create(this, R.raw.soundeffect)

        val btnRoll = findViewById<Button>(R.id.btnRoll)
        btnRoll.setOnClickListener {
            player.start()
            // disable the button click
            btnRoll.isEnabled = true
            getRandomValue()
        }

        // add dice images to list
        diceImages.add(R.drawable.dice_1)
        diceImages.add(R.drawable.dice_2)
        diceImages.add(R.drawable.dice_3)
        diceImages.add(R.drawable.dice_4)
        diceImages.add(R.drawable.dice_5)
        diceImages.add(R.drawable.dice_6)

        // initialize the animation
        animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.rotate_animation)

    }

    private fun getRandomValue() {
        val random = Random().nextInt(6) // returns number between 0-5

        // start the animation
        imgDice.startAnimation(animation)

        // now we will get when the animation starts and when it ends

        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                // when animation ends we will set the dice image
                imgDice.setImageResource(diceImages.elementAt(random)) // this will set the dice image randomly
                // enable the button click

            }

            override fun onAnimationStart(p0: Animation?) {
                // when animation starts we will set default dice image
                imgDice.setImageResource(R.drawable.empty_dice)
            }
        })
    }
}