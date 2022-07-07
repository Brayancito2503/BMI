package com.example.pesoapp.View.ViewModel

import java.io.Serializable

data class IndiceMasaCorporal(
    var pesos: MutableList<Float> = mutableListOf(),
    var alturas: MutableList<Float> = mutableListOf(),
) : Serializable {
    fun agregarDatos(nuevoPeso: Float, nuevaAltura: Float) {
        pesos.add(nuevoPeso)
        alturas.add(nuevaAltura)
    }
}