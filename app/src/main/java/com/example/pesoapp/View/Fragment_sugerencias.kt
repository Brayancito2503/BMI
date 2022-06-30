package com.example.pesoapp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pesoapp.R
import com.example.pesoapp.View.adapter.sugerenciaAdapter
import com.example.pesoapp.databinding.FragmentCalculadoraBinding
import com.example.pesoapp.databinding.FragmentSugerenciasBinding
import com.google.api.Distribution

class Fragment_sugerencias : Fragment() {

    private var fbinding: FragmentSugerenciasBinding? = null
    private val binding get() = fbinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fbinding = FragmentSugerenciasBinding.inflate(layoutInflater)
        val view = binding.root
        initRecyclerView()
        return view
    }

    fun initRecyclerView(){
        val manager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, manager.orientation)
        binding.recyclerSugerencia.layoutManager = manager
        binding.recyclerSugerencia.adapter = sugerenciaAdapter(sugerenciasProvider.sugerenciasLits)

        binding.recyclerSugerencia.addItemDecoration(decoration)
    }

}