package com.example.modul1_xml

import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var diceImage1: ImageView
    private lateinit var diceImage2: ImageView

    private val diceImages = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImage1 = findViewById(R.id.dice_image_1)
        diceImage2 = findViewById(R.id.dice_image_2)

        val rollButton: MaterialButton = findViewById(R.id.roll_button)
        val coordinatorLayout: androidx.coordinatorlayout.widget.CoordinatorLayout =
            findViewById(R.id.coordinator_layout)

        rollButton.setOnClickListener {
            val result1 = Random.nextInt(6)
            val result2 = Random.nextInt(6)

            diceImage1.setImageResource(diceImages[result1])
            diceImage2.setImageResource(diceImages[result2])

            val message = if (result1 == result2) {
                "Selamat, anda dapat dadu double!"
            } else {
                "Anda belum beruntung!"
            }

            Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}