package com.example.tipcalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addListenerOnSeekBar()

        var amount: EditText? = findViewById(R.id.editText)
        if (amount != null) {
            amount.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {
                    calculateTip()
                }
            })
        }
    }

    fun addListenerOnSeekBar() {
        var seekBar: SeekBar = findViewById(R.id.seekBar)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                var textView: TextView = findViewById(R.id.textView4)
                if (seekBar != null) {
                    textView.text = "${seekBar.progress}%";
                };

                calculateTip()
            }
        })
    }

    fun calculateTip() {
        var amount: EditText? = findViewById(R.id.editText)
        var seekBar: SeekBar = findViewById(R.id.seekBar)
        var textView: TextView = findViewById(R.id.textView5)
        var textView2: TextView = findViewById(R.id.textView6)
        var textView3: TextView = findViewById(R.id.textView7)
        var textView4: TextView = findViewById(R.id.textView8)

        if (amount != null) {
            if(amount.text.isNotEmpty()) {
                var fixedTipValue: Double = amount.text.toString().toDouble().times(15).div(100)
                textView.text = "$${fixedTipValue}"
                textView3.text = "$${amount.text.toString().toDouble().plus(fixedTipValue)}"

                var dynamicTipValue: Double =
                    amount.text.toString().toDouble().times(seekBar.progress.toString().toInt())
                        .div(100)
                textView2.text = "$${dynamicTipValue}"
                textView4.text = "$${amount.text.toString().toDouble().plus(dynamicTipValue)}"
            }
        }
    }
}
