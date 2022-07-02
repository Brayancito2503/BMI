package com.example.pesoapp.View

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.compose.ui.node.getOrAddAdapter
import com.airbnb.lottie.LottieAnimationView
import com.example.pesoapp.R
import com.example.pesoapp.databinding.FragmentCalculadoraBinding
import com.github.anastr.speedviewlib.components.Section
import com.github.anastr.speedviewlib.components.Style
import com.github.anastr.speedviewlib.components.note.Note
import com.github.anastr.speedviewlib.components.note.TextNote
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.properties.Delegates


class Fragment_Calculadora : Fragment() {

    //Declaracion de variables
    private var fbinding: FragmentCalculadoraBinding? = null
    private val binding get() = fbinding!!
    private lateinit var itemaltura: String
    private lateinit var itempeso: String

    var f1 = 0f
    var f2 = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        with(binding.autoCompleteTextView) {
            setAdapter(adapterEstatura)
            onItemClickListener = object : AdapterView.OnItemClickListener {
                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    itemaltura = p0?.getItemAtPosition(p2).toString()

                    if (itemaltura == "cm" || itemaltura == "m") {
                        binding.alturaTxt.isEnabled = true
                    }
//                        Toast.makeText(context, "hola Estatura $itemaltura", Toast.LENGTH_SHORT).show()
                }
            }
        }

        with(binding.autoCompleteTextView2) {
            setAdapter(adapterPeso)
            onItemClickListener = object : AdapterView.OnItemClickListener {
                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    itempeso = p0?.getItemAtPosition(p2).toString()

                    if (itempeso == "lb" || itempeso == "kg") {
                        binding.pesoTxt.isEnabled = true
                    }
//                        Toast.makeText(context, "hola Peso $itempeso", Toast.LENGTH_SHORT).show()
                }
            }
        }

//        val SpinnerAltura= binding.spinner1altura
//        val SpinnerPeso= binding.spinner2peso


//        //Spinner para Estatura
//        if(SpinnerAltura!=null){
//
//            SpinnerAltura.adapter=adapterEstatura
//
//            SpinnerAltura.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
//                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                    itemaltura=p2 // Variable que captura el indice del spinner de altura
//                }
//
//                override fun onNothingSelected(p0: AdapterView<*>?) {
//                    TODO("Not yet implemented")
//                }
//            }
//        }
//        //Spinner para peso
//        if(SpinnerPeso!=null){
//
//            SpinnerPeso.adapter=adapterPeso
//
//            SpinnerPeso.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
//                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                    itempeso=position // Variable que captura el indice del spinner de peso
//                }
//
//                override fun onNothingSelected(p0: AdapterView<*>?) {
//                    TODO("Not yet implemented")
//                }
//            }
//        }


        //secciones de la grafica
        val speedometer = binding.speedometer
        speedometer.makeSections(3, Color.BLUE, Style.BUTT)

        speedometer.sections[0].color = Color.BLUE
        speedometer.sections[1].color = Color.GREEN
        speedometer.sections[2].color = Color.RED
        speedometer.clearSections()

        speedometer.addSections(
            Section(0f, .45f, Color.rgb(33,165,243))
            , Section(.451f, .624f, Color.rgb(64,188,100))
            , Section(.625f, 1f, Color.rgb(251,83,70))

        )
//        , Section(.75F, .843F, Color.rgb(251,83,70))
//        , Section(.845F, 1F, Color.rgb(251,83,70))
        speedometer.minSpeed = 0F
        speedometer.maxSpeed = 40F
//        speedometer.clearSections()
//
//        speedometer.addSections(
//            Section(0F, .45f, Color.BLUE),
//            Section(.454f, .623f, Color.GREEN),
//            Section(.625f, 1f, Color.RED)

//        )
        var like = false
        speedometer.speedometerWidth = 65F

        binding.btnNombre.setOnClickListener {
            f1 = 2.7f
            f2 = 47.75f
        }

        binding.btnMujer.setOnClickListener {
            f1 = 2.25f
            f2 = 45f

        }


        binding.btnConfirmar.setOnClickListener {


            if (validarCampos().equals(true)) {


                val txtPeso = binding.pesoTxt
                val txtAltura = binding.alturaTxt
                val peso = txtPeso.text.toString().toFloat()
                val altura = txtAltura.text.toString().toFloat()
                var resultado: Float
                var pi: Double

                //altura == cm && Peso == lb
                if (itemaltura == "cm" && itempeso == "lb") {
                    resultado = (peso / 2.2F) / (altura / 100).pow(2)
                    pi = ((((altura - 152.4) / 2.54) * f1) + f2)
                    Toast.makeText(context, "El resultado es $resultado", Toast.LENGTH_SHORT).show()
                    resultados(resultado, pi)
                }
                //altura == cm && Peso == kg
                if (itemaltura == "cm" && itempeso == "kg") {
                    resultado = peso / (altura / 100).pow(2)
                    pi = ((((altura - 152.4) / 2.54) * f1) + f2)
                    Toast.makeText(context, "El resultado es $resultado", Toast.LENGTH_SHORT).show()
                    resultados(resultado, pi)
                }
                //altura == m && Peso == lb
                if (itemaltura == "m" && itempeso == "lb") {
                    resultado = (peso / 2.2F) / altura.pow(2)
                    pi = (((((altura * 100) - 152.4) / 2.54) * f1) + f2)
                    Toast.makeText(context, "El resultado es $resultado", Toast.LENGTH_SHORT).show()
                    resultados(resultado, pi)
                }
                //altura == m && Peso == kg
                if (itemaltura == "m" && itempeso == "kg") {
                    resultado = peso / altura.pow(2)
                    pi = (((((altura * 100) - 152.4) / 2.54) * f1) + f2)
                    Toast.makeText(context, "El resultado es $resultado", Toast.LENGTH_SHORT).show()
                    resultados(resultado, pi)
                }

                //Imprime los valores de indice de cada spinner
                Toast.makeText(context, " Spinner altura esta en: $itemaltura", Toast.LENGTH_SHORT)
                    .show()
                Toast.makeText(context, "Spinner peso esta en: $itempeso", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        return view
    }

    private fun likeAnimation(imageView: LottieAnimationView,
                              animation: Int,
                              like: Boolean) : Boolean {

        if (!like) {
            imageView.setAnimation(animation)
            imageView.playAnimation()

//            binding.btnConfirmar.visibility = 1
        }

        return like
    }

    fun Nota(Texto: String) {
        val speedometer = binding.speedometer
        val note = TextNote(requireContext(), Texto)
            .setPosition(Note.Position.CenterSpeedometer) // position of Note.
            .setAlign(Note.Align.Top) // Note Align.
            .setTextTypeFace(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC)) // style, or font.
            .setBackgroundColor(Color.parseColor("#fce3c8")) // change dialog color.
            .setCornersRound(20f) // dialog's rectangle Corners Round.
            .setTextSize(speedometer.dpTOpx(10f))

        speedometer.addNote(note, 8000)
    }


    fun resultados(resultado: Float, pi: Double) {

        val speedometer = binding.speedometer
        val lblPi = binding.pilbl
        val lblInc = binding.bmibl

        if (resultado < 18) {
            lblInc.text = "Debajo de lo normal $resultado"
            Nota("Delgadez")
        } else if (resultado >= 18.1 && resultado <= 24.9) {
            lblInc.text = "Peso Normal $resultado"
            Nota("Normal")
        } else if (resultado >= 25 && resultado <= 29.9) {
            lblInc.text = "Sobre Peso $resultado"
            Nota("Sobre Peso")
        } else if (resultado >= 30 && resultado <= 34.9) {
            lblInc.text = "Obesidad tipo I $resultado"
            Nota("Obesidad I")
        } else if (resultado > 35) {
            lblInc.text = "Obesidad tipo II $resultado"
            Nota("Obesidad II")
        }

        lblPi.text = "${pi.toInt()}"
        speedometer.speedTo(resultado, 4000)
    }

    fun validarCampos(): Boolean {
        try {
            var validar = false
            if (binding.alturaTxt.text?.length?.equals(0)!!) {
                binding.alturaTxt.requestFocus()
                binding.alturaTxt.setError("Debe ingresar valores")
                return validar
            }
            if (binding.pesoTxt.text?.length?.equals(0)!!) {
                binding.pesoTxt.requestFocus()
                binding.pesoTxt.setError("Debe ingresar valores")
                return validar
            }
            if(binding.btnNombre.isChecked || binding.btnMujer.isChecked){


            }else if(binding.btnNombre.isChecked == false){
                binding.btnNombre.requestFocus()
                binding.btnNombre.setError("Debe Selecionar una opcion")
                return validar
            }else if(binding.btnMujer.isChecked == false){
                binding.btnNombre.requestFocus()
                binding.btnNombre.setError("Debe Selecionar una opcion")
                return validar
            }


            validar = true
            return validar
        } catch (e: Exception) {
            e.message?.let { Log.e("Error en validar", it) };
            return false
        }
    }
}

