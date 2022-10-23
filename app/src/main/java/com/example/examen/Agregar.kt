package com.example.examen

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar.*
import kotlinx.android.synthetic.main.activity_agregar.txtCost
import kotlinx.android.synthetic.main.activity_agregar.txtDescription
import kotlinx.android.synthetic.main.activity_agregar.txtExisting
import kotlinx.android.synthetic.main.activity_agregar.txtName
import kotlinx.android.synthetic.main.activity_agregar.txtSale
import kotlinx.android.synthetic.main.activity_agregar.txtUrl
import kotlinx.android.synthetic.main.activity_editar.*

class Agregar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

        // upload the db and finish the activity
        btnConfirmar.setOnClickListener {

            var nombre = txtName.getText().toString()
            var descripcion = txtDescription.getText().toString()
            var existentes = txtExisting.getText().toString()
            var precioCosto = txtCost.getText().toString()
            var precioVenta = txtSale.getText().toString()
            var url = txtUrl.getText().toString()

            if (nombre == "") {
                Toast.makeText(this, "empty field", Toast.LENGTH_SHORT).show()
            } else {
                if (descripcion == "") {
                    Toast.makeText(this, "empty field", Toast.LENGTH_SHORT).show()
                } else {
                    if (existentes == "") {
                        Toast.makeText(this, "empty field", Toast.LENGTH_SHORT).show()
                    } else {
                        if (precioCosto == "") {
                            Toast.makeText(this, "empty field", Toast.LENGTH_SHORT).show()
                        } else {
                            if (precioVenta == "") {
                                Toast.makeText(this, "empty field", Toast.LENGTH_SHORT).show()
                            } else {

                                if (url == "") {
                                    Toast.makeText(this, "empty field", Toast.LENGTH_SHORT).show()
                                } else {
                                    // db adding
                                    val admin = AdminSQLiteOpenHelper(this, "products", null, 1)
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
                                    Toast.makeText(this, "Se cargaron los datos", Toast.LENGTH_LONG)
                                        .show()

                                    val intento7 = Intent(this, MainActivity::class.java)
                                    startActivity(intento7)
                                }
                            }
                        }
                    }
                }
            }
        }


        //regresa a la activity principal
        btnCancel.setOnClickListener {
            val intento5 = Intent(this, MainActivity::class.java)
            startActivity(intento5)
        }
/*
        // finish the activity when btnCancel clicked
        btnCancel.setOnClickListener{
            finish()
        }
*/

        /*// TEST / delete after
        btnReadName.setOnClickListener{

            // read by name
            val admin = AdminSQLiteOpenHelper(this, "products", null, 1)
            val bd = admin.writableDatabase
            println("bd")
            println(bd)
            val fila = bd.rawQuery("select descripcion,existentes,precioCosto,precioVenta,url from productos where nombre =' ${txtName.text.toString()}'", null)
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

        /*
        // upload the db and finish the activity
        btnConfirmar.setOnClickListener{
            // db adding
            val admin = AdminSQLiteOpenHelper(this,"products", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("nombre", txtName.getText().toString())
            registro.put("descripcion", txtDescription.getText().toString())
            registro.put("existentes", txtExisting.getText().toString())
            registro.put("precioCosto", txtCost.getText().toString())
            registro.put("precioVenta", txtSale.getText().toString())
            registro.put("url", txtUrl.getText().toString())
            println("registro")
            println(registro)
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
        btnReadName.setOnClickListener{

            // read by name
            val admin = AdminSQLiteOpenHelper(this, "products", null, 1)
            val bd = admin.writableDatabase
            println("bd")
            println(bd)
            val fila = bd.rawQuery("select id,descripcion,existentes,precioCosto,precioVenta,url from productos where nombre = ${txtName.text.toString()}", null)
            println("fila")
            println(fila)

            if (fila.moveToFirst()) {
                txtDescription.setText(fila.getString(0))
                txtExisting.setText(fila.getString(1))
                txtCost.setText(fila.getString(2))
                txtSale.setText(fila.getString(3))
                txtUrl.setText(fila.getString(4))
            } else
                Toast.makeText(this, "No existe un producto con ese nombre",  Toast.LENGTH_SHORT).show()
           bd.close()
        }*/
*/
    }
}