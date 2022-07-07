package com.example.pesoapp.View

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.pesoapp.R
import com.example.pesoapp.View.ViewModel.IndiceMasaCorporal
import com.example.pesoapp.databinding.FragmentHistorialBinding
import com.example.pesoapp.service.FirestoreService
import com.example.pesoapp.service.OnQueryCompletedCallback
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class Fragment_historial : Fragment() {

    private lateinit var binding: FragmentHistorialBinding

    lateinit var linelist: ArrayList<Entry>
    lateinit var lineDataSet: LineDataSet
    lateinit var lineData: LineData

    val db = FirestoreService()
    val userId = Firebase.auth.currentUser!!.uid
    var indiceMasaCorporal = IndiceMasaCorporal()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistorialBinding.inflate(layoutInflater)
        val view = binding.root
        // Inflate the layout for this fragment

        loadUser()


        linelist = ArrayList()
        var dia = 0f


        indiceMasaCorporal.pesos


        if (dia == 31f) {
            dia = 1f
            linelist.clear()
        }



        val peso: List<Float> = listOf(10F, 30F, 50F, 20F)

        val peso3 = indiceMasaCorporal.pesos



        for (elemento in indiceMasaCorporal.pesos){

            dia += 1
            var peso2 = elemento

            linelist.add(Entry(dia, peso2 ))
        }




        lineDataSet = LineDataSet(linelist, "Su peso")

        lineData = LineData(lineDataSet)

        binding.lineChart.data = lineData


        lineDataSet.setColors(*ColorTemplate.JOYFUL_COLORS)
        lineDataSet.valueTextColor = Color.BLUE
        lineDataSet.valueTextSize = 20f

        return view
    }

    private fun updateUserData() {
        db.update(userId, indiceMasaCorporal, object: OnQueryCompletedCallback {
            override fun <T> onSuccess(result: T) {
                Log.d(Fragment_Calculadora.TAG, "Datos de usuario $userId actualizados")
            }

            override fun <T> onFailure(result: T) {
                Log.w(Fragment_Calculadora.TAG, "Datos de usuario $userId no se han podido actualizar")
            }
        })
    }

    private fun loadUser() {
        db.getUserData(userId, object: OnQueryCompletedCallback {
            override fun <T> onSuccess(result: T) {
                val document = result as DocumentSnapshot
                Log.d(Fragment_Calculadora.TAG, "Document with id ${document.id} retrieved")
                val data = document.toObject<IndiceMasaCorporal>()
                if (data != null) {
                    Log.d(Fragment_Calculadora.TAG, "User exists, document retrieved as object")
                    indiceMasaCorporal = data
                }
                else {
                    Log.d(Fragment_Calculadora.TAG, "User doesn't exist, creating...")
                    createUser()
                }
            }

            override fun <T> onFailure(result: T) {
                Log.w(Fragment_Calculadora.TAG, "Couldn't get data for user $userId")
            }
        })
    }

    private fun createUser() {
        db.update(userId, indiceMasaCorporal, object: OnQueryCompletedCallback {
            override fun <T> onSuccess(result: T) {
                Log.d(Fragment_Calculadora.TAG, "Usuario $userId creado con exito")
            }
            override fun <T> onFailure(result: T) {
                Log.d(Fragment_Calculadora.TAG, "Fallo al crear usuario $userId")
            }
        })
    }


}