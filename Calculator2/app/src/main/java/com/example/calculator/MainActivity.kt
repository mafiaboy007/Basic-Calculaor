package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastisNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View) {
        val outputText = findViewById<TextView>(R.id.outputText)
        outputText.append((view as Button).text)
        lastisNumeric = true
    }
    fun onClear(view: View) {
        val outputText = findViewById<TextView>(R.id.outputText)
        outputText.text = ""
        lastisNumeric = false
        lastDot = false
    }

    fun onDot(view: View) {
        val outputText = findViewById<TextView>(R.id.outputText)
        if (lastisNumeric && !lastDot) {
            outputText.append(".")
            lastisNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        val outputText = findViewById<TextView>(R.id.outputText)
        if (lastisNumeric && !isOperatorAdded(outputText.text.toString())) {
            outputText.append((view as Button).text)
            lastDot = false
            lastisNumeric = false
        }
    }
    fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        }else {
            value.contains("/") or value.contains("+") or
            value.contains("-") or value.contains("*")
        }
    }

    fun onEqual(view: View) {
        val outputText = findViewById<TextView>(R.id.outputText)
        var outputFinal = outputText.text.toString()
        var prefix = ""
        try {
            if (outputFinal.startsWith("-")) {
                outputFinal = outputFinal.substring(1)
                prefix = "-"
            }
            if (outputFinal.contains("-")) {
                var splitdashit = outputFinal.split("-")

                var firstValue= splitdashit[0]
                var secondValue = splitdashit[1]

                if (!prefix.isEmpty()) {
                    firstValue = prefix + firstValue
                }
                outputText.text = removeZeroes((firstValue.toDouble() - secondValue.toDouble()).toString())
                if (outputText.text.length == 12) {
                    Toast.makeText(this, "Digit Limits", Toast.LENGTH_SHORT).show()
                }
            }
            if (outputFinal.contains("+")) {
                var splitdashit = outputFinal.split("+")

                var firstValue= splitdashit[0]
                var secondValue = splitdashit[1]

                outputText.text = removeZeroes((firstValue.toDouble() + secondValue.toDouble()).toString())
                if (outputText.text.length == 12) {
                    Toast.makeText(this, "Digit Limits", Toast.LENGTH_SHORT).show()
                }
            }

            if (outputFinal.contains("/")) {
                var splitdashit = outputFinal.split("/")

                var firstValue= splitdashit[0]
                var secondValue = splitdashit[1]

                outputText.text = removeZeroes((firstValue.toDouble() / secondValue.toDouble()).toString())
                if (outputText.text.length == 12) {
                    Toast.makeText(this, "Digit Limits", Toast.LENGTH_SHORT).show()
                }
            }
            if (outputFinal.contains("x")) {
                var splitdashit = outputFinal.split("x")

                var firstValue= splitdashit[0]
                var secondValue = splitdashit[1]

                outputText.text = removeZeroes((firstValue.toDouble() * secondValue.toDouble()).toString())
                if (outputText.text.length == 12) {
                    Toast.makeText(this, "Digit Limits", Toast.LENGTH_SHORT).show()
                }
            }

        }catch (e: ArithmeticException) {
            outputText.text = e.toString()
        }
    }
    private fun removeZeroes(str: String) : String {
        var returnStr = str
        if (str.contains(".0")) {
            returnStr = returnStr.substring(0, returnStr.length-2)
            return returnStr
        }else {
            return returnStr
        }
    }

}