package com.example.examen

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // add activity
        btnAdd.setOnClickListener {
            val intento1 = Intent(this, agregar::class.java)
            startActivity(intento1)
        }

        //salta a la activity EditarActivity
        btnEdit.setOnClickListener {
            val intento2 = Intent(this, EditarActivity::class.java)
            startActivity(intento2)
        }
    }
}