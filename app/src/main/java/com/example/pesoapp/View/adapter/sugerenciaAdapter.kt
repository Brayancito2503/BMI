package com.example.pesoapp.View.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pesoapp.R
import com.example.pesoapp.View.recordatorio

class sugerenciaAdapter(private val sugerenciaList:List<recordatorio>) : RecyclerView.Adapter<sugerenciaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sugerenciaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return sugerenciaViewHolder(layoutInflater.inflate(R.layout.item_recomendacion, parent, false))
    }

    override fun onBindViewHolder(holder: sugerenciaViewHolder, position: Int) {
        val item = sugerenciaList[position]
        holder.render(item)


    }

    override fun getItemCount(): Int = sugerenciaList.size

}