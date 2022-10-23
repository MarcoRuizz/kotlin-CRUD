package com.example.examen

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class Adapter(private val context: Activity, private val nombre: Array<String>, private val descripcion: Array<String>, private val existencia: Array<Int>, private val costoProducto: Array<Float>, private val venta: Array<Float>, private val imageID: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, nombre) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater

        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val titleText = rowView.findViewById(R.id.nombre) as TextView
        val subtitleText = rowView.findViewById(R.id.descripcion) as TextView
        val existeText = rowView.findViewById(R.id.existencia) as TextView
        val costText = rowView.findViewById(R.id.costoProducto) as TextView
        val ventaText = rowView.findViewById(R.id.venta) as TextView
        val imageView = rowView.findViewById(R.id.imageID) as ImageView

        titleText.text = nombre[position]
        //imageView.setImageResource(imageID[position])

        // debug
        println("URL: ${imageID[position]}")

        // add imageid
        Glide.with(context)
            .load(imageID[position])
            .error(R.drawable.sams_splash)
            .into(imageView)

        subtitleText.text = descripcion[position].toString()
        existeText.text = (existencia[position].toString())
        costText.text = (costoProducto[position].toString())
        ventaText.text = (venta[position].toString())

        return rowView
    }

}

private fun ImageView.setImageResource(s: String) {

}
