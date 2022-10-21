package com.example.examen

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_editar.*
import kotlinx.android.synthetic.main.activity_editar.txtCost
import kotlinx.android.synthetic.main.activity_editar.txtDescription
import kotlinx.android.synthetic.main.activity_editar.txtExisting
import kotlinx.android.synthetic.main.activity_editar.txtName
import kotlinx.android.synthetic.main.activity_editar.txtSale
import kotlinx.android.synthetic.main.activity_editar.txtUrl

class EditarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        btnSearch.setOnClickListener{

            // read by name
            val admin = AdminSQLiteOpenHelper(this, "products", null, 1)
            val bd = admin.writableDatabase
            println("bd")
            println(bd)
            val fila = bd.rawQuery("select descripcion,existentes,precioCosto,precioVenta,url from productos where nombre ='${txtName.text.toString()}'", null)
            println("fila")
            println(fila)

            if (fila.moveToFirst()) {
                txtDescription.setText(fila.getString(0))
                txtExisting.setText(fila.getString(1))
                txtCost.setText(fila.getString(2))
                txtSale.setText(fila.getString(3))
                txtUrl.setText(fila.getString(4))
            } else
                Toast.makeText(this, "No existe ese producto",  Toast.LENGTH_LONG).show()
            bd.close()
        }

        btnChange.setOnClickListener {

            val admin = AdminSQLiteOpenHelper(this, "products", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("descripcion", txtDescription.text.toString())
            registro.put("existentes", txtExisting.text.toString())
            registro.put("precioCosto", txtCost.text.toString())
            registro.put("precioVenta", txtSale.text.toString())
            registro.put("url", txtUrl.text.toString())
            val cant = bd.update("productos", registro, "nombre=${txtName.text.toString()}", null)
            bd.close()
            if (cant == 1)
                Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "no se actualizaron los datos", Toast.LENGTH_LONG).show()
        }


        //regresa a la activity principal
        btnReturn.setOnClickListener {
            val intento4 = Intent(this, MainActivity::class.java)
            startActivity(intento4)
        }
    }
}