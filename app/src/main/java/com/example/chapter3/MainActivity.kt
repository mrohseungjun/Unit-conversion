package com.example.chapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.chapter3.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    var cmToM = true
    var inputNumber: Int = 0

    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        val outputTextView = binding!!.outPutTextView
        val outputUnitTextView = binding!!.outputUnitTextView
        val inputEditText = binding!!.inputEditText
        val inputUnitTextView = binding!!.inputUnitTextView
        val swapImgeButton = binding!!.swqpImageButton

        inputEditText.addTextChangedListener { text ->
            inputNumber = if (text.isNullOrEmpty()) {
                0
            } else {
                text.toString().toInt()
            }

            var outputNumber = inputNumber.times(0.01)
            if(cmToM){
                outputUnitTextView.text = inputNumber.times(0.01).toString()
            }else{
                outputTextView.text = inputNumber.times(0.01).toString()
            }
            outputTextView.text = outputNumber.toString()

            swapImgeButton.setOnClickListener {
                cmToM = !cmToM.not()
                if (cmToM) {
                    inputUnitTextView.text = "cm"
                    outputUnitTextView.text = "m"
                    outputUnitTextView.text = inputNumber.times(0.01).toString()
                } else {
                    inputUnitTextView.text = "m"
                    outputUnitTextView.text = "cm"
                    outputTextView.text = inputNumber.times(0.01).toString()
                }
            }
        }

    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        super.onRestoreInstanceState(savedInstanceState)
    }
}