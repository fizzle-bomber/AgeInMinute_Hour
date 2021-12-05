package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var btn_selDate:Button
    var tvSelectDate:TextView? = null
    var tvInMinutes:TextView? = null
    var tvInHours:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_selDate = findViewById(R.id.button)
        btn_selDate.setOnClickListener{
            clickDatePicker()
        }
        tvSelectDate = findViewById(R.id.textView4)
        tvInMinutes = findViewById(R.id.textView6)
        tvInHours = findViewById(R.id.textView2)
    }

    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this ,
        { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            tvSelectDate?.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            theDate?.let {
                val selectedDateInTime = theDate.time / 60000
                val selectedDateInHour= theDate.time / 3600000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInMinutes = currentDate.time / 60000
                    val currentDateInHour = currentDate.time / 3600000
                    val diffInMinutes = currentDateInMinutes - selectedDateInTime
                    val diffInHours = currentDateInHour - selectedDateInHour
                    tvInMinutes?.text = diffInMinutes.toString()
                    tvInHours?.text = diffInHours.toString()
            }
        }

        },
            year ,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}