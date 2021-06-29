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


        diceImages.add(R.drawable.dice_1)
        diceImages.add(R.drawable.dice_2)
        diceImages.add(R.drawable.dice_3)
        diceImages.add(R.drawable.dice_4)
        diceImages.add(R.drawable.dice_5)
        diceImages.add(R.drawable.dice_6)



        animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.rotate_animation)

    }

    private fun getRandomValue() {
        val random = Random().nextInt(6)


        imgDice.startAnimation(animation)



        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {

                imgDice.setImageResource(diceImages.elementAt(random))


            }

            override fun onAnimationStart(p0: Animation?) {

                imgDice.setImageResource(R.drawable.empty_dice)
            }
        })
    }
}