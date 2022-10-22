package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        /*
        if(fila.moveToFirst()){
            println(fila.getString(0))
            println(fila.getString(1))
            println(fila.getString(2))
            println(fila.getString(3))
            println(fila.getString(4))
            println(fila.getString(5))

            nombre.plusElement(fila.getString(0))
            descripcion.plusElement(fila.getString(1))
            existencia.plusElement(fila.getInt(2))
            costoProducto.plusElement(fila.getFloat(3))
            venta.plusElement(fila.getFloat(4))
            imageID.plusElement(fila.getString(5))
        }
         */

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
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
            Toast.makeText(this, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
        }

        // delete database register
        btnDelete.setOnClickListener{
            val admin = AdminSQLiteOpenHelper(this, "products", null, 1)
            val bd = admin.writableDatabase

            /*
            // get clicked id
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
            val cant = bd.delete("productos", "codigo=${itemIdAtPos}", null)
            bd.close()
            if (cant == 1)
                Toast.makeText(this, "Se borró el artículo con dicho código", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No existe un artículo con dicho código", Toast.LENGTH_SHORT).show()
            */
        }

        // ACTIVITIES

        // add acivitiy
        btnAdd.setOnClickListener {
            val intento1 = Intent(this, Agregar::class.java)
            startActivity(intento1)
        }

        // edit activity
        btnEdit.setOnClickListener {
            val intento2 = Intent(this, EditarActivity::class.java)
            startActivity(intento2)
        }



    }
}