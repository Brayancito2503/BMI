package com.example.pesoapp.View
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.pesoapp.R
import com.example.pesoapp.databinding.FragmentCalculadoraBinding
import com.github.anastr.speedviewlib.components.Style
import kotlin.math.pow
import kotlin.properties.Delegates


class Fragment_Calculadora : Fragment(){

    //Declaracion de variables
    private var fbinding: FragmentCalculadoraBinding? = null
    private val binding get() = fbinding!!
    private var itemaltura by Delegates.notNull<Int>()
    private var itempeso by Delegates.notNull<Int>()

    var f1 = 0f
    var f2 = 0f

    var item:String = ""

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
        //Spinners
        val SpinnerAltura= binding.spinner1altura
        val SpinnerPeso= binding.spinner2peso

        //Spinner para Estatura
        if(SpinnerAltura!=null){

            SpinnerAltura.adapter=adapterEstatura

            SpinnerAltura.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    itemaltura=p2 // Variable que captura el indice del spinner de altura
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
        //Spinner para peso
        if(SpinnerPeso!=null){

            SpinnerPeso.adapter=adapterPeso

            SpinnerPeso.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    itempeso=p2 // Variable que captura el indice del spinner de peso
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }


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
            var resultado = peso / (altura / 100).pow(2)
            var pi =((((altura - 152.4) / 2.54) * f1) + f2)

            //Imprime los valores de indice de cada spinner
            Toast.makeText(context," Spinner altura indice es: $itemaltura",Toast.LENGTH_SHORT).show()
            Toast.makeText(context,"Spinner peso indice es: $itempeso",Toast.LENGTH_SHORT).show()



            if (resultado < 18) {
                lblInc.text = "Debajo de lo normal $resultado"
            } else if (resultado >= 18.1 && resultado <= 24.9) {
                lblInc.text = "Peso Normal $resultado"
            } else if (resultado >= 25 && resultado <= 29.9) {
                lblInc.text = "Sobre Peso $resultado"
            } else if (resultado >= 30 && resultado <= 34.9) {
                lblInc.text = "Obesidad tipo I $resultado"
            } else if (resultado > 35) {
                lblInc.text = "Obesidad tipo II $resultado"
            }
            lblPi.text = "$pi"
            Toast.makeText(context,"$peso",Toast.LENGTH_SHORT).show()
            speedometer.speedTo(resultado, 4000)

        }

        return view

    }



}

