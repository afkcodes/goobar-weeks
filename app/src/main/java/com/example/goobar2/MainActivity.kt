package com.example.goobar2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipcodeEditText: EditText = findViewById(R.id.enterZip)
        val submitButton:Button = findViewById(R.id.submit_button)

        submitButton.setOnClickListener {
            var zipcode:String = zipcodeEditText.text.toString()
            if (zipcode.length < 5){
                zipcode = "Enter Valid ZipCode"
            }
            Toast.makeText(this,zipcode, Toast.LENGTH_LONG).show()
        }
    }
}