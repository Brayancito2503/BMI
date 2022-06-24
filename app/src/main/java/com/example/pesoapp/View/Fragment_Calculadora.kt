package com.example.pesoapp.View

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pesoapp.R
import com.example.pesoapp.databinding.FragmentCalculadoraBinding
import com.github.anastr.speedviewlib.SpeedView
import com.github.anastr.speedviewlib.components.Section
import com.github.anastr.speedviewlib.components.Style
import java.lang.Boolean.TRUE
import kotlin.math.pow


class Fragment_Calculadora : Fragment(), AdapterView.OnItemClickListener {

    //Declaracion de variables
    private var fbinding: FragmentCalculadoraBinding? = null
    private val binding get() = fbinding!!

    var f1 = 0f
    var f2 = 0f

    var item:String = ""
    var item2:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fbinding = FragmentCalculadoraBinding.inflate(layoutInflater)
        val view = binding.root

        val medidasEstatura = resources.getStringArray(R.array.Estatura)
        val medidasPeso = resources.getStringArray(R.array.Peso)
        val adapterEstatura = context?.let {
            ArrayAdapter(
                it,
                R.layout.list_item,
                medidasEstatura
            )
        }


        val adapterPeso = context?.let {
            ArrayAdapter(
                it,
                R.layout.list_item_peso,
                medidasPeso
            )
        }
//
        with(binding.autoCompleteEstaturaMedida){
            setAdapter(adapterEstatura)
            onItemClickListener = this@Fragment_Calculadora
//            onItemSelectedListener = this@Fragment_Calculadora
        }
        with(binding.autoCompletePesoMedida){
            setAdapter(adapterPeso)
            onItemClickListener = this@Fragment_Calculadora
//            onItemSelectedListener = this@Fragment_Calculadora
        }


//        val speedometer = findViewById<SpeedView>(R.id.speedometer)

        //secciones de la grafica
        val speedometer = binding.speedometer
        speedometer.makeSections(3, Color.BLUE, Style.BUTT)

        speedometer.sections[0].color = Color.BLUE
        speedometer.sections[1].color = Color.GREEN
        speedometer.sections[2].color = Color.RED
//        speedometer.clearSections()
//        speedometer.addSections(
//            Section(0f, .46f, Color.LTGRAY)
//            , Section(.46f, .625f, Color.YELLOW)
//            , Section(.625f, 1f, Color.BLUE)
//        )
//
//        speedometer.speedometerWidth = 80F


        binding.btnNombre.setOnClickListener{
            f1 = 2.7f
            f2 = 47.75f
        }

        binding.btnMujer.setOnClickListener{
            f1 = 2.25f
            f2 = 45f
        }

        binding.btnConfirmar.setOnClickListener {
            val speedometer = binding.speedometer
            val lblPi = binding.pilbl
            val lblInc = binding.bmibl
            val txtPeso = binding.pesoTxt
            val txtAltura = binding.alturaTxt
            val peso = txtPeso.text.toString().toFloat()
            val altura = txtAltura.text.toString().toFloat()
            var resultado:Float
            var pi: Double

//            if(item == "cm" && item2 == "lb"){
//
//                resultado = ((peso/2.2) / (altura / 100).pow(2)).toFloat()
//                pi = ((((altura - 152.4) / 2.54) * f1) + f2)
//                Toast.makeText(context, "entro al primero < Pi $pi > <R $resultado >", Toast.LENGTH_SHORT).show()
//                if (resultado < 18) {
//                    lblInc.text = "Debajo de lo normal $resultado"
//                } else if (resultado >= 18.1 && resultado <= 24.9) {
//                    lblInc.text = "Peso Normal $resultado"
//                } else if (resultado >= 25 && resultado <= 29.9) {
//                    lblInc.text = "Sobre Peso $resultado"
//                } else if (resultado >= 30 && resultado <= 34.9) {
//                    lblInc.text = "Obesidad tipo I $resultado"
//                } else if (resultado > 35) {
//                    lblInc.text = "Obesidad tipo II $resultado"
//                }
//                lblPi.text = "$pi"
//                Toast.makeText(context,"$peso",Toast.LENGTH_SHORT).show()
//                speedometer.speedTo(resultado, 4000)
//            }else if(item == "m" && item2 == "lb"){
//
//
//                resultado = ((peso/2.2)/((altura).pow(2))).toFloat()
//                pi = (((((altura * 100 ) - 152.4) / 2.54) * f1) + f2)
//
//                Toast.makeText(context, "entro al segungo < Pi $pi > <R $resultado >", Toast.LENGTH_SHORT).show()
//                if (resultado < 18) {
//                    lblInc.text = "Debajo de lo normal $resultado"
//                } else if (resultado >= 18.1 && resultado <= 24.9) {
//                    lblInc.text = "Peso Normal $resultado"
//                } else if (resultado >= 25 && resultado <= 29.9) {
//                    lblInc.text = "Sobre Peso $resultado"
//                } else if (resultado >= 30 && resultado <= 34.9) {
//                    lblInc.text = "Obesidad tipo I $resultado"
//                } else if (resultado > 35) {
//                    lblInc.text = "Obesidad tipo II $resultado"
//                }
//                lblPi.text = "$pi"
//                Toast.makeText(context,"$peso",Toast.LENGTH_SHORT).show()
//                speedometer.speedTo(resultado, 4000)
//            }
//
//            resultado = peso / (altura / 100).pow(2)
//            if (resultado < 18) {
//                lblInc.text = "Debajo de lo normal $resultado"
//            } else if (resultado >= 18.1 && resultado <= 24.9) {
//                lblInc.text = "Peso Normal $resultado"
//            } else if (resultado >= 25 && resultado <= 29.9) {
//                lblInc.text = "Sobre Peso $resultado"
//            } else if (resultado >= 30 && resultado <= 34.9) {
//                lblInc.text = "Obesidad tipo I $resultado"
//            } else if (resultado > 35) {
//                lblInc.text = "Obesidad tipo II $resultado"
//            }
//            lblPi.text = "$pi"
//            Toast.makeText(context,"$peso",Toast.LENGTH_SHORT).show()
//            speedometer.speedTo(resultado, 4000)

        }


//        val speedometer =  binding<SpeedView>(R.id.speedometer)

        return view

    }


//    val aux:String = item
//    var aux2:String = item2


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val hola = resources.getStringArray(R.array.Estatura)
        item = hola.get(position)
        val hola2 = resources.getStringArray(R.array.Peso)
        item2 = hola2.get(position)

//        Toast.makeText(context, aux, Toast.LENGTH_SHORT).show()
        Toast.makeText(context, "item 1 $item y item 2 $item2", Toast.LENGTH_SHORT).show()


        if(item == "cm" || item == "m"){
            binding.alturaTxt.isEnabled = true
            Toast.makeText(context,"es el item 1 $item", Toast.LENGTH_SHORT).show()
        }
        if(item2 == "lb" || item2 == "kg"){
            binding.pesoTxt.isEnabled = true
            Toast.makeText(context,"es el item 2 $item2", Toast.LENGTH_SHORT).show()
        }
//
//        if(item == "cm" && item2 == "lb"){
//            Toast.makeText(context, "si sirve item 1 <$item > item 2 <$item2> ", Toast.LENGTH_SHORT).show()
//        }
//


    }



}