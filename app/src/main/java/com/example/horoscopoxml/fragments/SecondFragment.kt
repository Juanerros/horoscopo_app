package com.example.horoscopoxml.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.horoscopoxml.R
import com.example.horoscopoxml.data.horoscopoData
import com.example.horoscopoxml.data.signosData
import com.google.android.material.button.MaterialButton
import kotlin.random.Random

class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_second, container, false)

        val btnNav = root.findViewById<MaterialButton>(R.id.btnNav2)

        btnNav.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }

        var selectedSigno:String = ""

        val signos = signosData.listSignos
        val dropdown = root.findViewById<AutoCompleteTextView>(R.id.listSignos)
        val nameSignos = signos.map { it.name }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            nameSignos
        )

        dropdown.setAdapter(adapter)

        dropdown.setOnItemClickListener { parent, view, position, id ->
            selectedSigno = parent.getItemAtPosition(position).toString()
        }
        
        val btnCalcular = root.findViewById<MaterialButton>(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            if(!selectedSigno.isNullOrEmpty()) {
                dropdown.setText(selectedSigno, false)


                //esto


                val txtH = root.findViewById<TextView>(R.id.txtHoroscopo)
                val posi = Random.nextInt(0, 13)
                val newText = horoscopoData.listHoroscopo[posi]
                selectedSigno = dropdown.text.toString()
                txtH.text = newText
            } 

        }
        return root
    }
}