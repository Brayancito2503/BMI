package com.example.pesoapp.View

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pesoapp.R
import com.example.pesoapp.databinding.FragmentCalculadoraBinding
import com.github.anastr.speedviewlib.components.Section
import com.github.anastr.speedviewlib.components.Style
import com.github.anastr.speedviewlib.components.note.Note
import com.github.anastr.speedviewlib.components.note.TextNote
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_sugerencias.*
import kotlin.math.pow


class Fragment_Calculadora : Fragment() {

    //Declaracion de variables
    private var fbinding: FragmentCalculadoraBinding? = null
    private val binding get() = fbinding!!
    private lateinit var itemaltura: String
    private lateinit var itempeso: String
    var resultado: Float = 0.0f
    lateinit var linelist: ArrayList<Entry>
    lateinit var lineDataSet: LineDataSet
    lateinit var lineData: LineData
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

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

        ///////////////////////////////////////////////////////////////////////
        linelist = ArrayList()
        var dia = 0f


        //////////////////////////////////////////////////////////////////////////

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

        ///////////////////////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////////////////////

        //secciones de la grafica
        val speedometer = binding.speedometer
        speedometer.makeSections(3, Color.BLUE, Style.BUTT)

        speedometer.sections[0].color = Color.BLUE
        speedometer.sections[1].color = Color.GREEN
        speedometer.sections[2].color = Color.RED
        speedometer.clearSections()

        speedometer.addSections(
            Section(0f, .45f, Color.rgb(33, 165, 243)),
            Section(.45f, .625f, Color.rgb(64, 188, 100)),
            Section(.625f, 1f, Color.rgb(251, 83, 70))

        )
        speedometer.minSpeed = 0F
        speedometer.maxSpeed = 40F
//        speedometer.speedometerWidth = 120F
        speedometer.speedometerWidth = 80F
        //Funcion Click Radio Button Hombre
        binding.btnNombre.setOnClickListener {
            f1 = 2.7f
            f2 = 47.75f
            binding.btnNombre.error = null
            binding.btnMujer.error = null
        }
        //Funcion Click Radio Button Mujer
        binding.btnMujer.setOnClickListener {
            f1 = 2.25f
            f2 = 45f
            binding.btnNombre.error = null
            binding.btnMujer.error = null
        }

        //Boton salir
//        binding.buttonsalir.setOnClickListener {
//            signOut()
//        }

        val dato = db.collection("prueba")

// Para agregar
//        val dato = db.collection("prueba").document()
//        val datos = hashMapOf(
//            "apellido" to "potosme"
//        )
//        dato.set(datos)
//            .addOnSuccessListener { resultado ->
//
//
//                Log.d("TAGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG", "SI ENTROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\nAKJSDAKJSDHKAJSDHKAJSDHKJASDHKAJSDHKAJSDH\nASDJASDKJASDADSASDDA")
//
//            }.addOnFailureListener{exepcion ->
//                Log.d("TAG", "NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\nError ${exepcion}")
//            }
        //Funcion Boton Confirmar
        binding.btnConfirmar.setOnClickListener {

            // para traer datos
//            val dato = db.collection("prueba")
//            var datos = " "
//
//            dato.get()
//                .addOnSuccessListener { resultado ->
//                    for(docuemnto in resultado){
//                       datos += "${docuemnto.id} => ${docuemnto.data}"
//                    }
//
//                    binding.pruba.text = datos
//                }.addOnFailureListener{exepcion ->
//                    Log.d("TAG", "Error ${exepcion}")
//                }

            val dato = db.collection("prueba")


            var datos = " "
            dato.whereEqualTo("apellido", 1)
                .get()
                .addOnSuccessListener { resultado ->
                    for(docuemnto in resultado){
                        datos = "${docuemnto.data}"
                    }

                  binding.pruba.text = datos
                }.addOnFailureListener{exepcion ->
                    Log.d("TAG", "Error ${exepcion}")
                }





            if (validarCampos()) {
                val txtPeso = binding.pesoTxt
                val txtAltura = binding.alturaTxt
                val peso = txtPeso.text.toString().toFloat()
                val altura = txtAltura.text.toString().toFloat()
                var pi: Double
                dia = dia + 1


                if (dia == 31f) {
                    dia = 1f
                    linelist.clear()
                }


                        linelist.add(Entry(dia, peso))

                        lineDataSet = LineDataSet(linelist, "Su peso")

                        lineData = LineData(lineDataSet)

                        binding.lineChart.data = lineData


                lineDataSet.setColors(*ColorTemplate.JOYFUL_COLORS)
                lineDataSet.valueTextColor = Color.BLUE
                lineDataSet.valueTextSize = 20f
                //altura == cm && Peso == lb
                if (itemaltura == "cm" && itempeso == "lb") {
                    resultado = (peso / 2.2F) / (altura / 100).pow(2)
                    pi = ((((altura - 152.4) / 2.54) * f1) + f2) * 2.2F
                    resultados(resultado, pi, "Libras")
                }
                //altura == cm && Peso == kg
                if (itemaltura == "cm" && itempeso == "kg") {
                    resultado = peso / (altura / 100).pow(2)
                    pi = ((((altura - 152.4) / 2.54) * f1) + f2)
                    resultados(resultado, pi, "Kilos")
                }
                //altura == m && Peso == lb
                if (itemaltura == "m" && itempeso == "lb") {
                    resultado = (peso / 2.2F) / altura.pow(2)
                    pi = (((((altura * 100) - 152.4) / 2.54) * f1) + f2) * 2.2F
                    resultados(resultado, pi, "Libras")
                }
                //altura == m && Peso == kg
                if (itemaltura == "m" && itempeso == "kg") {
                    resultado = peso / altura.pow(2)
                    pi = (((((altura * 100) - 152.4) / 2.54) * f1) + f2)
                    resultados(resultado, pi, "Kilos")
                }
            }
        }
        //resultadofinal.add(resultado)

        return view
    }


    fun Nota(Texto: String) {
        val speedometer = binding.speedometer
        val note = TextNote(requireContext(), Texto)
            .setPosition(Note.Position.CenterSpeedometer) // position of Note.
            .setAlign(Note.Align.Top) // Note Align.
            .setTextTypeFace(
                Typeface.create(
                    Typeface.DEFAULT,
                    Typeface.BOLD_ITALIC
                )
            ) // style, or font.
            .setBackgroundColor(Color.parseColor("#fce3c8")) // change dialog color.
            .setCornersRound(20f) // dialog's rectangle Corners Round.
            .setTextSize(speedometer.dpTOpx(20f))
        speedometer.addNote(note, 8000)
    }

    //Funcion para calcular el rango del bmi

    fun resultados(resultado: Float, pi: Double, texto: String) {

        val speedometer = binding.speedometer
        val lblPi = binding.pilbl
        val lblInc = binding.bmibl
        val txtpeso = binding.pesoTxt
        val peso = txtpeso.text.toString().toFloat()

        if (resultado < 18) {
            lblInc.text = "Debajo de lo normal ${resultado.toFloat()}"
            Nota("Delgadez")
        } else if (resultado.dec() in 18.1..24.9) {
            lblInc.text = "Peso Normal ${resultado.dec()}"
            if (pi.toInt() == peso.toInt()) {
                Nota("ideal")
            } else {
                Nota("Normal")
            }
        } else if (resultado in 25.0..29.9) {
            lblInc.text = "Sobre Peso ${resultado.dec()}"
            Nota("Sobre Peso")
        } else if (resultado in 30.0..34.9) {
            lblInc.text = "Obesidad tipo I ${resultado.dec()}"
            Nota("Obesidad I")
        } else if (resultado > 35) {
            lblInc.text = "Obesidad tipo II ${resultado.dec()}"
            Nota("Obesidad II")
        }

        lblPi.text = "${pi.toInt()} $texto"
        speedometer.speedTo(resultado.dec(), 4000)
    }

    fun validarCampos(): Boolean {
        try {
            var validar1 = false
            var validar2 = false
            var validar3 = false
            var validatodo = false

            validar1 = if (binding.alturaTxt.text.isNotEmpty()) {
                true
            } else {
                binding.alturaTxt.requestFocus()
                binding.alturaTxt.error = "Debe ingresar valores"
                false
            }
            validar2 = if (binding.pesoTxt.text.isNotEmpty()) {
                true
            } else {
                binding.pesoTxt.requestFocus()
                binding.pesoTxt.error = "Debe ingresar valores"
                false
            }
            validar3 = if (binding.btnNombre.isChecked || binding.btnMujer.isChecked) {
                true
            } else {
                binding.btnNombre.error = "Seleccione una opcion"
                binding.btnMujer.error = "Seleccione una opcion"
                binding.radiogrupo.requestFocus()

                false
            }
            if ((validar1 && validar2 && validar3) == true) {

                validatodo = true
            }

            return validatodo
        } catch (e: Exception) {
            e.message?.let { Log.e("Error en validar", it) }
            return false
        }
    }
}

