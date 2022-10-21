package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

// create arrays
val imageID = arrayOf<Int>(R.drawable.sams_splash)
val nombre = arrayOf<String>()
val descripcion = arrayOf<String>()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get data base
        val admin = AdminSQLiteOpenHelper(this, "products", null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("select nombre,descripcion from productos where url = 'url'", null)
        println("FILA:")
        println(fila)

        if(fila.moveToFirst()){
            println(fila.getString(0))
            println(fila.getString(1))

            nombre.plusElement(fila.getString(0))
            descripcion.plusElement(fila.getString(1))
        }
        bd.close()
        println("ARRAYS DATA:")
        println(Arrays.toString(nombre))
        println(Arrays.toString(descripcion))

        /*
        // save all the db data on arrays
        val data = bd.rawQuery("select * from productos", null);

        while(data.moveToNext()){
            val uname = data.getInt(data.getInt(id));
            productoId.plus(uname); // plusElement ???
        }

        bd.close() // delete dbclose of line 35 if used
         */

        // print database listview
        val myListAdapter = Adapter(this,imageID,nombre,descripcion)
        listView.adapter = myListAdapter
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

        // add activity
        btnAdd.setOnClickListener {
            val intento1 = Intent(this, Agregar::class.java)
            startActivity(intento1)
        }

        //salta a la activity EditarActivity
        btnEdit.setOnClickListener {
            val intento2 = Intent(this, EditarActivity::class.java)
            startActivity(intento2)
        }



    }
}