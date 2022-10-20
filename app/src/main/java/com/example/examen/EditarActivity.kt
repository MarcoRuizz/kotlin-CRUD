package com.example.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_editar.*
import kotlinx.android.synthetic.main.activity_editar.txtName

class EditarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        btnAgregar.setOnClickListener {
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

        //regresa a la activity principal
        btnReturn.setOnClickListener {
            val intento4 = Intent(this, MainActivity::class.java)
            startActivity(intento4)
        }
    }
}