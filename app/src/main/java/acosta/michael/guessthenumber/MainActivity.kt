package acosta.michael.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var maxNum = 100
    var minNum = 0
    var num: Int = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessing: TextView = findViewById(R.id.guessings)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        generate.setOnClickListener {
            num = Random.nextInt(minNum, maxNum)
            guessing.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener {
            minNum = num
            if (checkingLimits()){
                num = Random.nextInt(minNum, maxNum)
                guessing.setText(num.toString())
            } else {
                guessing.setText("Wonderful, you win :)")
            }

        }

        down.setOnClickListener {
            maxNum = num
            if (checkingLimits()) {
                num = Random.nextInt(minNum, maxNum)
                guessing.setText(num.toString())
            } else {
                guessing.setText("Wonderful, you win :)")
            }
        }

        guessed.setOnClickListener {
            if(!won) {
                up.visibility = View.INVISIBLE
                down.visibility = View.INVISIBLE
                guessing.setText("i Found it, your number is " + num)
                guessed.setText("Try again?")
                won = true
            } else {
                generate.visibility = View.VISIBLE
                up.visibility = View.VISIBLE
                down.visibility = View.VISIBLE
                guessing.setText("Tap on generate to start")
                guessed.setText("Guessed")
                guessed.visibility = View.GONE
                resetValues()
            }
        }

    }

    fun resetValues(){
        minNum = 0
        maxNum = 100
        num = 0
        won = false
    }

    fun checkingLimits(): Boolean{
        return minNum != maxNum
    }
}