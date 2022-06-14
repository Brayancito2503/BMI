package com.example.pesoapp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pesoapp.R
import com.example.pesoapp.databinding.FragmentCalculadoraBinding
import kotlin.math.pow


class Fragment_Calculadora : Fragment() {

    lateinit var binding: FragmentCalculadoraBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__calculadora, container, false)
    }

    var f1 = 0f
    var f2 = 0f

    fun btnHombreOnClick(v: View?) {
        f1 = 2.7f
        f2 = 47.75f
    }

    fun btnMujerOnClick(v: View?) {
        f1 = 2.25f
        f2 = 45f
    }

//    fun btnConfirmarOnClick(v: View) {
//        val lblPi = findViewById<View>(R.id.pilbl) as TextView
//        val lblInc = findViewById<View>(R.id.bmibl) as TextView
//        val txtPeso = findViewById<View>(R.id.pesoTxt) as TextView
//        val txtAltura = findViewById<View>(R.id.alturaTxt) as TextView
//        val peso = txtPeso.text.toString().toFloat()
//        val altura = txtAltura.text.toString().toFloat()
//        val resultado = peso / (altura/100).pow(2)
//        val pi: Double = ((((altura - 152.4) / 2.54 )* f1) + f2)
//        if (resultado < 18) {
//            lblInc.text = "Debajo de lo normal $resultado"
//        } else if (resultado >= 18.1 && resultado <= 24.9) {
//            lblInc.text = "Peso Normal $resultado"
//        } else if (resultado >= 25 && resultado <= 29.9) {
//            lblInc.text = "Sobre Peso $resultado"
//        } else if (resultado >= 30 && resultado <= 34.9) {
//            lblInc.text = "Obesidad tipo I $resultado"
//        } else if (resultado > 35) {
//            lblInc.text = "Obesidad tipo II $resultado"
//        }
//        lblPi.text = "$pi"
//    }

}