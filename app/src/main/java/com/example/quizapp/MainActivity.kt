package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 *  Kotlin Android Application: QuizzApp
 *
 *  Author: Mauro Travieso (986965)
 *
 *  Version: 1.0
 *  Bugs: none known
 */
class MainActivity : AppCompatActivity() {
    var total:Int = 0
    var times:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Listener implementation for the Radio buttons
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // Get which radio button is clicked
            val clicked = radioGroup.findViewById(checkedId) as RadioButton
            val checked = clicked.isChecked
            if (checked) {
                Toast.makeText(
                    applicationContext,
                    clicked.text.toString() + " Selected",
                    Toast.LENGTH_SHORT
                ).show()
                if (clicked.text.toString().equals("3")) {
                    total = total + 50
                } else {
                    total = 0
                }
            }
        }

        // Listener implementation for the check boxes
        checkBox1.setOnCheckedChangeListener {buttonView1, isChecked ->
            if (isChecked) {
                Toast.makeText(
                    applicationContext,
                    "You have selected option 1",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        checkBox2.setOnCheckedChangeListener {buttonView2, isChecked ->
            if (isChecked) {
                Toast.makeText(
                    applicationContext,
                    "You have selected option 2",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        checkBox3.setOnCheckedChangeListener {buttonView3, isChecked ->
            if (isChecked) {
                Toast.makeText(
                    applicationContext,
                    "You have selected option 3",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    fun onSubmit(view : View) {
        if (times==false){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Submit")
            builder.setMessage("Do you want to submit_icon?")
            builder.setIcon(R.drawable.submit_icon)

            builder.setPositiveButton("Yes") { dialogInterface, which ->

                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)+1
                val day = c.get(Calendar.DAY_OF_MONTH)

                val hour = c.get(Calendar.HOUR_OF_DAY)
                val minutes = c.get(Calendar.MINUTE)

                if (checkBox1.isChecked && checkBox2.isChecked && checkBox3.isChecked) {
                    total += 50
                }
                textView2.setTypeface(null, Typeface.BOLD_ITALIC)
                textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
                textView2.setTextColor(Color.parseColor("#6200EE"))
                textView2.text =
                    "Congratulations! You submitted on $month/$day/$year\n" +  "at $hour:$minutes. " + "Your achieved: " + total.toString()+"%"
                times = true

                Toast.makeText(applicationContext, "Submit done!", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }

            builder.setNegativeButton("No") { dialogInterface, which ->
                Toast.makeText(applicationContext, "Submit canceled!", Toast.LENGTH_SHORT).show()
            }

            val dialog: AlertDialog = builder.create()

            dialog.show()
        }


    }

    fun onReset(view : View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Reset")
        builder.setMessage("Do you want to restart? (All your information will be lost)")
        builder.setIcon(R.drawable.reset_icon)

        builder.setPositiveButton("Yes") { dialogInterface, which ->
            radioButton1.setChecked(false)
            radioButton2.setChecked(false)
            radioButton3.setChecked(false)
            checkBox1.setChecked(false)
            checkBox2.setChecked(false)
            checkBox3.setChecked(false)
            textView2.text = ""
            times=false
            total = 0

            Toast.makeText(applicationContext, "Reset done!", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }

        builder.setNegativeButton("No") { dialogInterface, which ->
            Toast.makeText(applicationContext, "Reset canceled!", Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Exit"){ dialogInterface, which ->
            finish()
        }

        val dialog: AlertDialog = builder.create()

        dialog.show()

    }
}