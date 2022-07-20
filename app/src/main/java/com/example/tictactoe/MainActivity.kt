package com.example.tictactoe

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var onBackPressedTime: Long = 0
    private var winX: Int = 0
    private var winY: Int = 0


    /* 0->x(red) 1->o(blue)  */
    private val random = java.util.Random()
    var activePlayer = random.nextInt(2)

    var isGameActive = true

    //2 o'ynalmagan kataklar
    var gameState = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    //yutish kataklari
    var winningPositions = arrayOf(
        //Horizontal winning positions:
        arrayOf(0, 1, 2),
        arrayOf(3, 4, 5),
        arrayOf(6, 7, 8),
        //Vertical
        arrayOf(0, 3, 6),
        arrayOf(1, 4, 7),
        arrayOf(2, 5, 8),
        //diagonal
        arrayOf(0, 4, 8),
        arrayOf(2, 4, 6)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(activePlayer == 0){
            view_line1.visibility = View.VISIBLE
        }else  view_line2.visibility = View.VISIBLE

        val namePlayer1: String? = intent.getStringExtra("player1")
        val namePlayer2: String? = intent.getStringExtra("player2")
        player1.text = namePlayer1
        cpuOrPlayer.text = namePlayer2
    }

    fun click(view: View) {
        if (isGameActive){
            val button = view as ImageView
            val tapped = button.tag.toString().toInt()
            if(gameState[tapped] == 2){
                gameState[tapped] = activePlayer
                if (activePlayer == 0){
                    view_line1.visibility = View.INVISIBLE
                    view_line2.visibility = View.VISIBLE
                    button.setImageResource(R.drawable.icon_x)
                    activePlayer = 1
                }
                else{
                   view_line2.visibility = View.INVISIBLE
                    view_line1.visibility = View.VISIBLE
                    button.setImageResource(R.drawable.icon_o)
                    activePlayer = 0
                }
            }
            for (winningPositions in winningPositions){
                if (gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                        gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                        gameState[winningPositions[0]] != 2){
                    isGameActive = false
                    game_over_layout.visibility = View.VISIBLE
                    text_congratulate.visibility = View.VISIBLE

                    var winner: String? = intent.getStringExtra("player1")
                    textResult.setTextColor(Color.RED)
                    textResult.text = winner
                    if (gameState[winningPositions[0]] == 1){
                        winY++
                        score_blue.text = winY.toString()
                        winner = intent.getStringExtra("player2")
                        textResult.setTextColor(Color.BLUE)
                        textResult.text = winner
                        text_congratulate.visibility = View.VISIBLE
                    }
                    else{
                        winX++
                        score_red.text = winX.toString()
                    }
                }
                else{
                    var isGameOver = true
                    //Draw
                    for (state in gameState){
                        if (state == 2) isGameOver = false
                    }
                    if (isGameOver){
                        textResult.text = "it is a draw"
                        game_over_layout.visibility = View.VISIBLE
                        text_congratulate.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    fun playAgain(view: View){
        isGameActive = true
        game_over_layout.visibility = View.INVISIBLE
        activePlayer = random.nextInt(2)
        if (activePlayer == 0){
            view_line1.visibility = View.VISIBLE
            view_line2.visibility = View.INVISIBLE
        }else  {
            view_line2.visibility = View.VISIBLE
            view_line1.visibility = View.INVISIBLE
        }
        for (i in 0..gameState.lastIndex){
            gameState[i] = 2
        }
        for (i in 0 until gridContainer.childCount){
            (gridContainer.getChildAt(i) as ImageView).setImageResource(0)
        }
    }

    fun buttonTapped(tapped:Int, button: ImageView){
        if(gameState[tapped] == 2){
            gameState[tapped] = activePlayer
            if (activePlayer == 0){
                view_line1.visibility = View.INVISIBLE
                view_line2.visibility = View.VISIBLE
                button.setImageResource(R.drawable.icon_x)
                activePlayer = 1
            }
            else{
                view_line2.visibility = View.INVISIBLE
                view_line1.visibility = View.VISIBLE
                button.setImageResource(R.drawable.icon_o)
                activePlayer = 0
            }
        }
    }

    override fun onBackPressed() {
        if (onBackPressedTime + 2000 > System.currentTimeMillis()) {
            val intent = Intent(applicationContext, MenuActivity::class.java)
            startActivity(intent)
            super.onBackPressed()
        } else {
            Toast.makeText(
                applicationContext,
                "Please press back again to return to the menu!",
                Toast.LENGTH_SHORT
            ).show()
            onBackPressedTime = System.currentTimeMillis()
        }
    }
}