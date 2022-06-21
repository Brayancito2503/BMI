package com.example.pesoapp.View

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.example.pesoapp.R
import com.github.anastr.speedviewlib.SpeedView
import com.github.anastr.speedviewlib.components.Style
import kotlin.math.pow

class Activity_calculadora : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        val speedometer = findViewById<SpeedView>(R.id.speedometer)

        speedometer.makeSections(3, Color.BLUE, Style.BUTT)

        speedometer.sections[0].color = Color.rgb(32,165,242)
        speedometer.sections[1].color = Color.rgb(252, 84,73)
        speedometer.sections[2].color = Color.rgb(63,189,99)


        val check = findViewById<CheckBox>(R.id.btnNombre)
    }


    var f1 = 0f
    var f2 = 0f

    fun btnHombreOnClick(v: View) {
        f1 = 2.7f
        f2 = 47.75f
    }

    fun btnMujerOnClick(v: View) {
        f1 = 2.25f
        f2 = 45f
    }

    fun btnConfirmarOnClick(v: View) {
        val speedometer = findViewById<SpeedView>(R.id.speedometer)
        // move to 50 Km/s
//        speedometer.speedTo(20.00F)

        val lblPi = findViewById<View>(R.id.pilbl) as TextView
        val lblInc = findViewById<View>(R.id.bmibl) as TextView
        val txtPeso = findViewById<View>(R.id.pesoTxt) as TextView
        val txtAltura = findViewById<View>(R.id.alturaTxt) as TextView
        val peso = txtPeso.text.toString().toFloat()
        val altura = txtAltura.text.toString().toFloat()
        val resultado = peso / (altura/100).pow(2)
        val pi: Double = ((((altura - 152.4) / 2.54 )* f1) + f2)
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

        speedometer.speedTo(resultado,4000)
    }
}