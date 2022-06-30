package com.example.pesoapp.View.adapter


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pesoapp.R
import androidx.fragment.app.Fragment
import com.example.pesoapp.View.recordatorio
import com.example.pesoapp.databinding.ItemRecomendacionBinding
import kotlinx.coroutines.withContext
import kotlin.contracts.contract
import androidx.fragment.app.DialogFragment

class sugerenciaViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemRecomendacionBinding.bind(view)


    fun render(RecordatorioModel: recordatorio) {
        binding.TvTitulo.text = RecordatorioModel.titulo
        binding.TvSitioWeb.text = RecordatorioModel.sitioWeb
        Glide.with(binding.imgFoto.context).load(RecordatorioModel.foto).into(binding.imgFoto)

        binding.imgFoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(binding.TvSitioWeb.text.toString())
            startActivity(itemView.context, intent, intent.extras)
//            Toast.makeText(binding.imgFoto.context, binding.TvSitioWeb.text, Toast.LENGTH_SHORT).show()
        }

//        itemView.setOnClickListener {
//
//
//        }
    }




}