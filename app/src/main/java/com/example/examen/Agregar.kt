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
    }
}