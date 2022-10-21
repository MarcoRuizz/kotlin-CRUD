package com.example.examen

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class Adapter(private val context: Activity, private val imageID: Array<Int>, private val nombre: Array<String>, private val descripcion: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, nombre) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater

        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val subtitleText = rowView.findViewById(R.id.description) as TextView

        titleText.text = nombre[position]
        imageView.setImageResource(imageID[position])
        subtitleText.text = descripcion[position]

        return rowView
    }
}
