package com.example.examen

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

// create arrays
var nombre = arrayOf<String>()
var descripcion = arrayOf<String>()
var existencia = arrayOf<Int>()
var costoProducto = arrayOf<Float>()
var venta = arrayOf<Float>()
var imageID = arrayOf<String>()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get data base
        val admin = AdminSQLiteOpenHelper(this, "products", null, 1)
        val bd = admin.writableDatabase
        println("bd")
        println(bd)
        val fila = bd.rawQuery("select nombre, descripcion, existentes, precioCosto, precioVenta, url from productos", null)
        println("FILA:")
        println(fila)

        // print how many registers on our db
        val countRegister = bd.rawQuery("select count(id) from productos", null)
        if (countRegister.moveToFirst()){
            println()
            println("${countRegister.getString(0)} registros")
        }
        val counter = countRegister.getString(0).toInt()

        // save all registers
        var i = 1;
        while(i <= counter){

            // names
            val saveNames = bd.rawQuery("select nombre, descripcion, existentes, precioCosto, precioVenta, url from productos where id =' ${i}'", null)
            if(saveNames.moveToFirst()){

                // debug
                println("Registro ${i}")
                println(saveNames.getString(0))
                println(saveNames.getString(1))
                println(saveNames.getInt(2))
                println(saveNames.getFloat(3))
                println(saveNames.getFloat(4))
                println(saveNames.getString(5))

                // add to array
                nombre += (saveNames.getString(0))
                descripcion += (saveNames.getString(1))
                existencia += (saveNames.getInt(2))
                costoProducto += (saveNames.getFloat(3))
                venta += (saveNames.getFloat(4))
                imageID += (saveNames.getString(5))

            }

            i++
        }

        bd.close()
        println("ARRAYS DATA:")
        println(Arrays.toString(nombre))
        println(Arrays.toString(descripcion))
        println(Arrays.toString(existencia))
        println(Arrays.toString(costoProducto))
        println(Arrays.toString(venta))
        println(Arrays.toString(imageID))

        // print database listview
        val myListAdapter = Adapter(this, nombre,descripcion, existencia, costoProducto, venta, imageID)
        listView.adapter = myListAdapter

        // listview on click event, send to another activity and send the id parameter
        listView.setOnItemClickListener(){adapterView, view, position, id ->
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            // id plus 1 because the db ids starts with 1

            val parameter = itemIdAtPos + 1
           Toast.makeText(this, "El id es: $itemIdAtPos", Toast.LENGTH_LONG).show()

            val intento8 = Intent(this, EditarActivity::class.java)
            startActivity(intento8)

            val parameter = itemIdAtPos.toInt() + 1
            println("ID SENDED: ${parameter}")

            // send the parameter to edit activity
            val intento1 = Intent(this, EditarActivity::class.java)
            intento1.putExtra("id", parameter)
            startActivity(intento1)
        }





        // ACTIVITIES

        // add acivitiy
        btnAdd.setOnClickListener {
            val intento1 = Intent(this, Agregar::class.java)
            startActivity(intento1)
        }

    }
}


