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

        val bundle = intent.extras
        var id = bundle?.getInt("id")
        println(id)
        //println("ID received ${id}")

        val admin = AdminSQLiteOpenHelper(this, "products", null, 1)
        val bd = admin.writableDatabase
        val registro = bd.rawQuery("select nombre, descripcion,existentes,precioCosto,precioVenta,url from productos where id=${id}" , null)
        if (registro.moveToFirst()) {
            txtName.setText(registro.getString(0))
            txtDescription.setText(registro.getString(1))
            txtExisting.setText(registro.getString(2))
            txtCost.setText(registro.getString(3))
            txtSale.setText(registro.getString(4))
            txtUrl.setText(registro.getString(5))

        }

        bd.close()
        /*
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
        */

        //
        btnSearch.setOnClickListener{

            // read by name
            val admin = AdminSQLiteOpenHelper(this, "products", null, 1)
            val bd = admin.writableDatabase
            println("bd")
            println(bd)

            // print how many registers on our db
            val countRegister = bd.rawQuery("select count(id) from productos", null)
            if (countRegister.moveToFirst()){
                println("regisros")
                println(countRegister.getString(0))
            }
            val counter = countRegister.getString(0).toInt()

            // print all registers
            var i = 1;
            while(i <= counter){
                println("Registro ${i}")
                i++;
            }

            // get the register data by name
            val fila = bd.rawQuery("select id,descripcion,existentes,precioCosto,precioVenta,url from productos where nombre ='${txtName.text}'", null)
            println("product name to edit: ")
            println(txtName.text.toString())
            println("fila columns given:")
            println(fila.columnNames.asList())
            println("fila moves to first")
            println(fila.moveToFirst())

            if (fila.moveToFirst()) {
                id = fila.getString(0).toInt()
                txtDescription.setText(fila.getString(1))
                txtExisting.setText(fila.getString(2))
                txtCost.setText(fila.getString(3))
                txtSale.setText(fila.getString(4))
                txtUrl.setText(fila.getString(5))
            } else
                Toast.makeText(this, "No existe ese producto",  Toast.LENGTH_LONG).show()
            bd.close()
        }
         //

        btnChange.setOnClickListener {

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
                                    val admin = AdminSQLiteOpenHelper(this, "products", null, 1)
                                    val bd = admin.writableDatabase
                                    val registro = ContentValues()
                                    registro.put("id", id)
                                    println(id)
                                    registro.put("nombre", txtName.text.toString())
                                    registro.put("descripcion", txtDescription.text.toString())
                                    registro.put("existentes", txtExisting.text.toString())
                                    registro.put("precioCosto", txtCost.text.toString())
                                    registro.put("precioVenta", txtSale.text.toString())
                                    registro.put("url", txtUrl.text.toString())
                                    val cant = bd.update("productos", registro, "id=${id}", null)
                                    bd.close()
                                    if (cant == 1) {
                                        Toast.makeText(
                                            this,
                                            "se modificaron los datos",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        // clear inputs after modifying the db
                                        txtName.setText("")
                                        txtDescription.setText("")
                                        txtExisting.setText("")
                                        txtCost.setText("")
                                        txtSale.setText("")
                                        txtUrl.setText("")
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "no se actualizaron los datos",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        // delete database register
        btnDelete.setOnClickListener{
            val admin = AdminSQLiteOpenHelper(this, "products", null, 1)
            val bd = admin.writableDatabase
            val cant = bd.delete("productos", "id=${id}", null)
            bd.close()
            txtName.setText("")
            txtDescription.setText("")
            txtExisting.setText("")
            txtCost.setText("")
            txtSale.setText("")
            txtUrl.setText("")

            if (cant == 1)
                Toast.makeText(this, "Product deleted", Toast.LENGTH_SHORT).show()

        }

        // return to activityMain
        btnReturn.setOnClickListener {
            val intento4 = Intent(this, MainActivity::class.java)
            startActivity(intento4)
        }
    }
}