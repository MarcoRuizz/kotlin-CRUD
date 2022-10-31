package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create arrays
        var nombre = arrayOf<String>()
        var descripcion = arrayOf<String>()
        var existencia = arrayOf<Int>()
        var costoProducto = arrayOf<Float>()
        var venta = arrayOf<Float>()
        var imageID = arrayOf<String>()

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

        // will be used on the while to save all the registers to the arrays
        var lastID = 1;

        // get the last id from the database
        val lastROW =  bd.rawQuery("select * from SQLITE_SEQUENCE", null)
        if(lastROW.moveToFirst()){
            // print the sql query
            println("LASTROW: ${lastROW.getString(0)}")
            println("LASTROW: ${lastROW.getString(1)}")

            // save the last id
            lastID = lastROW.getString(1).toInt()
            println("LASTID: ${lastID}")
        }

        // save all registers
        var i = 1;
        while(i <= lastID){
            // debug
            println("counter: ${i} / lastID: ${lastID}")

            // names
            val saveNames = bd.rawQuery("select id, nombre, descripcion, existentes, precioCosto, precioVenta, url from productos where id =' ${i}'", null)
            if(saveNames.moveToFirst()){

                // debug
                println(
                    "Registro ${i}: " +
                            "${saveNames.getInt(0)}, " +
                            "${saveNames.getString(1)}, " +
                            "${saveNames.getString(2)}, " +
                            "${saveNames.getInt(3)}, " +
                            "${saveNames.getFloat(4)}, " +
                            "${saveNames.getFloat(5)}, " +
                            "${saveNames.getString(6)}",
                )

                // add to array
                nombre += (saveNames.getString(1))
                descripcion += (saveNames.getString(2))
                existencia += (saveNames.getInt(3))
                costoProducto += (saveNames.getFloat(4))
                venta += (saveNames.getFloat(5))
                imageID += (saveNames.getString(6))
            }

            i += 1
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

            // get text string from the item clicked
            val item = adapterView.getItemAtPosition(position) as String
            println("LISTVIEW: ${item}")

            // send the parameter to edit activity
            val intento1 = Intent(this, EditarActivity::class.java)
            intento1.putExtra("id", parameter)
            startActivity(intento1)
        }

        // add acivitiy
        btnAdd.setOnClickListener {
            val intento1 = Intent(this, Agregar::class.java)
            startActivity(intento1)
        }
    }
}


