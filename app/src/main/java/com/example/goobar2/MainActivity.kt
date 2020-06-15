package com.example.goobar2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private val forecastRepository = ForecastRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipcodeEditText: EditText = findViewById(R.id.enterZip)
        val submitButton:Button = findViewById(R.id.submit_button)

        submitButton.setOnClickListener {
            var zipcode: String = zipcodeEditText.text.toString()
            if (zipcode.length < 5){
                Toast.makeText(this,"Enter Valid ZipCode", Toast.LENGTH_LONG).show()
            }
            forecastRepository.loadForecast(zipcode)
        }

        val forecastlist:RecyclerView = findViewById(R.id.forecastList)
        forecastlist.layoutManager = LinearLayoutManager(this)

        val dailyForecastAdapter = DailyForecastAdapter(){
            val msg = getString(R.string.toast_temp_msg, it.temp, it.description)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        forecastlist.adapter = dailyForecastAdapter

        val weeklyForecastObserver = Observer<List<DailyForecast>> {forecastItems->
            dailyForecastAdapter.submitList(forecastItems)

        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)
    }
}