package com.example.tictactoe

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bot.*
import kotlinx.android.synthetic.main.activity_bot.game_over_layout
import kotlinx.android.synthetic.main.activity_bot.textResult
import kotlinx.android.synthetic.main.activity_bot.text_congratulate
import kotlinx.android.synthetic.main.activity_main.*

class BotActivity : AppCompatActivity() {
    private var onBackPressedTime: Long = 0
    private var winX: Int = 0
    private var winY: Int = 0
    /* 0->x(red) 1->o(blue)*/
    private val cpu: Int = 1
    private val player: Int = 0

    private val random = java.util.Random()
    var activePlayer = random.nextInt(2)

    var isGameActive = true
    //o'ynalmagan kataklar
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
        setContentView(R.layout.activity_bot)
        if(activePlayer == 0){
            view_line1_bot.visibility = View.VISIBLE
        }else  bot_view_line2.visibility = View.VISIBLE

        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.custom_dailog, null)
        val editText = view.findViewById<EditText>(R.id.editText)
        val buttonCancel = view.findViewById<Button>(R.id.button_cancel)
        val buttonOk = view.findViewById<Button>(R.id.button_ok)

        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        buttonOk.setOnClickListener{
            dialog.dismiss()
            if (editText.text.toString().isNotEmpty()){
                player1_bot.text = editText.text.toString()
            }else{
                player1_bot.text = "Player"
            }

        }
        buttonCancel.setOnClickListener{
            dialog.dismiss()
        }

    }

    fun gameProcess() {
        if (isGameActive) {
            if (activePlayer == 0){
                //player moves
            }else{
                //cpu moves
            }

        }

    }

    fun playAgain(view: View) {

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