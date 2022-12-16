package com.example.a05_internalstorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a05_internalstorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBtnWrite()
        setupBtnRead()
    }

    private fun setupBtnWrite(){
        binding.writeButton.setOnClickListener {
            val filename = binding.filename.text.toString().plus(".txt")
            val userInput = binding.userInput.text.toString()

            writeData(filename, userInput)
        }
    }
    private fun setupBtnRead(){
        binding.readButton.setOnClickListener {
            val filename = binding.filename.text.toString().plus(".txt")
            readData(filename)
        }
    }

    private fun readData(filename: String){
        this.openFileInput(filename).bufferedReader().useLines {
            it.fold("") { some, text ->
                binding.content.setText(text).toString()
            }
        }
    }

    private fun writeData(filename: String, userInput: String){
        this.openFileOutput(filename,Context.MODE_PRIVATE).use {
            it.write(userInput.toByteArray())
        }
    }
}