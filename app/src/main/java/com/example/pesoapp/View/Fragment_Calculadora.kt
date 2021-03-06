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
import com.example.pesoapp.View.ViewModel.IndiceMasaCorporal
import com.example.pesoapp.databinding.FragmentCalculadoraBinding
import com.example.pesoapp.service.FirestoreService
import com.example.pesoapp.service.OnQueryCompletedCallback
import com.github.anastr.speedviewlib.components.Section
import com.github.anastr.speedviewlib.components.Style
import com.github.anastr.speedviewlib.components.note.Note
import com.github.anastr.speedviewlib.components.note.TextNote
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_sugerencias.*
import kotlin.math.pow


class Fragment_Calculadora : Fragment() {

    companion object {
        const val TAG = "FragmentCalculadora"
    }

    //Declaracion de variables
    private var fbinding: FragmentCalculadoraBinding? = null
    private val binding get() = fbinding!!
    private lateinit var itemaltura: String
    private lateinit var itempeso: String
    var resultado: Float = 0.0f


    val db = FirestoreService()
    val userId = Firebase.auth.currentUser!!.uid
    var indiceMasaCorporal = IndiceMasaCorporal()

    var f1 = 0f
    var f2 = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fbinding = FragmentCalculadoraBinding.inflate(layoutInflater)
        val view = binding.root

        loadUser()

        val medidasEstatura = resources.getStringArray(R.array.Estatura)
        val medidasPeso = resources.getStringArray(R.array.Peso)

        ///////////////////////////////////////////////////////////////////////


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

        //Funcion Boton Confirmar
        binding.btnConfirmar.setOnClickListener {

            if (validarCampos()) {

                val txtPeso = binding.pesoTxt
                val txtAltura = binding.alturaTxt
                val peso = txtPeso.text.toString().toFloat()
                val altura = txtAltura.text.toString().toFloat()
                var pi: Double


                updateIMC(peso, altura)



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

    private fun updateIMC(peso: Float, altura: Float) {
        var nuevaAltura = altura
        var nuevoPeso = peso

        if (itemaltura == "m") nuevaAltura *= 100
        if (itempeso == "kg") nuevoPeso *= 2.2f

        indiceMasaCorporal.agregarDatos(nuevoPeso, nuevaAltura)

        updateUserData()
    }

    private fun updateUserData() {
        db.update(userId, indiceMasaCorporal, object: OnQueryCompletedCallback {
            override fun <T> onSuccess(result: T) {
                Log.d(TAG, "Datos de usuario $userId actualizados")
            }

            override fun <T> onFailure(result: T) {
                Log.w(TAG, "Datos de usuario $userId no se han podido actualizar")
            }
        })
    }

    private fun loadUser() {
        db.getUserData(userId, object: OnQueryCompletedCallback {
            override fun <T> onSuccess(result: T) {
                val document = result as DocumentSnapshot
                Log.d(TAG, "Document with id ${document.id} retrieved")
                val data = document.toObject<IndiceMasaCorporal>()
                if (data != null) {
                    Log.d(TAG, "User exists, document retrieved as object")
                    indiceMasaCorporal = data
                }
                else {
                    Log.d(TAG, "User doesn't exist, creating...")
                    createUser()
                }
            }

            override fun <T> onFailure(result: T) {
                Log.w(TAG, "Couldn't get data for user $userId")
            }
        })
    }

    private fun createUser() {
        db.update(userId, indiceMasaCorporal, object: OnQueryCompletedCallback {
            override fun <T> onSuccess(result: T) {
                Log.d(TAG, "Usuario $userId creado con exito")
            }
            override fun <T> onFailure(result: T) {
                Log.d(TAG, "Fallo al crear usuario $userId")
            }
        })
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

