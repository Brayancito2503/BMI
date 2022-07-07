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
    var indiceMasaCorporal = MutableLiveData<IndiceMasaCorporal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistorialBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        loadUser()

        linelist = ArrayList()
        var dia = 0f

        if (dia == 31f) {
            dia = 1f
            linelist.clear()
        }

        indiceMasaCorporal.observe(viewLifecycleOwner) {
            indiceMasaCorporal.value?.let {
                for (peso in it.pesos) {
                    dia += 1
                    linelist.add(Entry(dia, peso))
                    lineDataSet = LineDataSet(linelist, "Su peso")
                    lineData = LineData(lineDataSet)
                    binding.lineChart.data = lineData
                }
            }
        }

        lineDataSet = LineDataSet(linelist, "Su peso")
        lineData = LineData(lineDataSet)
        binding.lineChart.data = lineData

        lineDataSet.setColors(*ColorTemplate.JOYFUL_COLORS)
        lineDataSet.valueTextColor = Color.BLUE
        lineDataSet.valueTextSize = 20f

        return view
    }

    private fun loadUser() {
        db.getUserData(userId, object: OnQueryCompletedCallback {
            override fun <T> onSuccess(result: T) {
                val document = result as DocumentSnapshot
                val data = document.toObject<IndiceMasaCorporal>()
                if (data != null) {
                    indiceMasaCorporal.value = data
                }
            }

            override fun <T> onFailure(result: T) {
                Log.w(Fragment_Calculadora.TAG, "Couldn't get data for user $userId")
            }
        })
    }
}