package com.example.calculatora2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private enum class Operators(val sign: Char) {
    MINUS('-'),
    PLUS('+'),
    MULTIPLY('*'),
    DIVISION('/'),
    POWER('^');
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // display window
        val displayWindow: TextView = findViewById(R.id.displayWindow)

        // all of the number buttons
        val buttonNumber0: Button = findViewById(R.id.button_0)
        listener(buttonNumber0, "0")
        //buttonNumber0.setOnClickListener {displayWindow.text = displayWindow.text.toString().plus("0")}
        val buttonNumber1: Button = findViewById(R.id.button_1)
        listener(buttonNumber1, "1")
        val buttonNumber2: Button = findViewById(R.id.button_2)
        listener(buttonNumber2, "2")
        val buttonNumber3: Button = findViewById(R.id.button_3)
        listener(buttonNumber3, "3")
        val buttonNumber4: Button = findViewById(R.id.button_4)
        listener(buttonNumber4, "4")
        val buttonNumber5: Button = findViewById(R.id.button_5)
        listener(buttonNumber5, "5")
        val buttonNumber6: Button = findViewById(R.id.button_6)
        listener(buttonNumber6, "6")
        val buttonNumber7: Button = findViewById(R.id.button_7)
        listener(buttonNumber7, "7")
        val buttonNumber8: Button = findViewById(R.id.button_8)
        listener(buttonNumber8, "8")
        val buttonNumber9: Button = findViewById(R.id.button_9)
        listener(buttonNumber9, "9")

        // all of the math buttons
        val buttonMathAdd: Button = findViewById(R.id.button_add)
        listener(buttonMathAdd, "+")
        val buttonMathSubtract: Button = findViewById(R.id.button_subtract)
        listener(buttonMathSubtract, "-")
        val buttonMathMultiply: Button = findViewById(R.id.button_multiply)
        listener(buttonMathMultiply, "*")
        val buttonMathDivide: Button = findViewById(R.id.button_divide)
        listener(buttonMathDivide, "/")

        // the extra option buttons
        val buttonHelpIcon: ImageView = findViewById(R.id.helpIcon)
        buttonHelpIcon.setOnClickListener {
            val toast = Toast.makeText(this, "This is a calculator! Use numbers to make more numbers", Toast.LENGTH_LONG)
            toast.show()
        }


        val buttonOptionClear: Button = findViewById(R.id.button_clear)
        //buttonOptionClear.setOnClickListener {displayWindow.text = displayWindow.text.toString().dropLast(1)} // 1 click removes the last thing
        buttonOptionClear.setOnClickListener { displayWindow.text = "" } // 1 click removes all
        //buttonOptionClear.setOnTouchListener{displayWindow.text = displayWindow.text.toString().dropLast(1)} // hold click removes everything


        val buttonOptionDecimal: Button = findViewById(R.id.button_decimal)
        listener(buttonOptionDecimal, ".")

        val buttonOptionEnter: Button = findViewById(R.id.button_enter)
        //buttonOptionEnter.setOnClickListener { parseAndCalculation(displayWindow.text.toString()) }
        buttonOptionEnter.setOnClickListener { mathSolver() }

    }

    private fun listener(
        button: Button,
        text: String,
        display: TextView = findViewById(R.id.displayWindow)
    ) {
        button.setOnClickListener { display.text = display.text.toString().plus(text) }
    }

    private fun mathSolver(display: TextView = findViewById(R.id.displayWindow)) {
        var currentDisplay = display.text.toString()
        if (currentDisplay.equals("error", true)) {
            display.text = "error".also { return }
        }
        val solver = ExpressionParser()


        var result: String = solver.evaluate(display.text.toString())
        display.text = result

    }
}