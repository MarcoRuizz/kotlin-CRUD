package com.example.examen

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar.*
import kotlinx.android.synthetic.main.activity_main.*

class agregar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

        // upload the db and finish the activity
        btnSave.setOnClickListener{
            // db adding
            val admin = AdminSQLiteOpenHelper(this,"administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("nombre", txtName.getText().toString())
            registro.put("descripcion", txtDescription.getText().toString())
            registro.put("existentes", txtExisting.getText().toString())
            registro.put("precioCosto", txtCost.getText().toString())
            registro.put("precioVenta", txtSale.getText().toString())
            registro.put("url", txtUrl.getText().toString())
            bd.insert("productos", null, registro)
            bd.close()

            // clear inputs after saving the db
            txtName.setText("")
            txtDescription.setText("")
            txtExisting.setText("")
            txtCost.setText("")
            txtSale.setText("")
            txtUrl.setText("")

            // send notification and finish the activity
            Toast.makeText(this, "Se cargaron los datos del artículo", Toast.LENGTH_SHORT).show()
            finish()
        }

        // finish the activity when btnCancel clicked
        btnCancel.setOnClickListener{
            finish()
        }

        // TEST / delete after
        btnRead.setOnClickListener{

            // read by name
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select descripcion,existentes,precioCosto,precioVenta,url from productos where nombre=${txtName.text.toString()}", null)
            if (fila.moveToFirst()) {
                txtDescription.setText(fila.getString(0))
                txtExisting.setText(fila.getString(1))
                txtCost.setText(fila.getString(2))
                txtSale.setText(fila.getString(3))
                txtUrl.setText(fila.getString(4))
            } else
                Toast.makeText(this, "No existe un producto con ese nombre",  Toast.LENGTH_SHORT).show()
           bd.close()
        }

/*
        btnBuscarProducto.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "datos", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select descripcion,existentes, precioCosto, precioVenta, url from productos where id=${txtName.text.toString()}", null)
            if (fila.moveToFirst()) {
                txtDescription.setText(fila.getString(0))
                txtExisting.setText(fila.getString(1))
                txtCost.setText(fila.getString(2))
                txtSale.setText(fila.getString(3))
                txtUrl.setText(fila.getString(4))
            } else
                Toast.makeText(this, "No existe el producto",  Toast.LENGTH_SHORT).show()
            bd.close()
        }
*/
    }
}