package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        players_btn.visibility = View.INVISIBLE
        playerVsCpu_btn.visibility = View.INVISIBLE
        val anim1 = AnimationUtils.loadAnimation(this,R.anim.movement)
        val anim2 = AnimationUtils.loadAnimation(this, R.anim.move)
        val rot = AnimationUtils.loadAnimation(this, R.anim.rotation)
        players_btn.startAnimation(anim1)
        playerVsCpu_btn.startAnimation(anim2)

        players_btn.visibility = View.VISIBLE
        playerVsCpu_btn.visibility = View.VISIBLE
     players_btn.setOnClickListener{
      pVsP_layout.visibility = View.VISIBLE
     }
        playerVsCpu_btn.setOnClickListener{
            val intent = Intent(this, BotActivity::class.java)
            coin_x.startAnimation(rot)
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(intent)
                finish()
            },1800)
        }

            playButton.setOnClickListener{
                if (editTextP1.text.toString().isNotEmpty() && editTextP2.text.toString().isNotEmpty()){
                    val intent = Intent(this, MainActivity::class.java)
                    var player1 = editTextP1.text.toString()
                    var player2 = editTextP2.text.toString()
                    intent.putExtra("player1", player1)
                    intent.putExtra("player2", player2)
                    coin_x.startAnimation(rot)
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(intent)
                        finish()
                    },2400)


                }
                else{
                    Toast.makeText(this, "Please enter the name of the Players", Toast.LENGTH_SHORT).show()
                }
            }


    }
}