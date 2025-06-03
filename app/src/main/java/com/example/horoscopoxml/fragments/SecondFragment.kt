package com.example.horoscopoxml.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.horoscopoxml.R
import com.example.horoscopoxml.data.signosData
import com.google.android.material.button.MaterialButton

class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_second, container, false)

        val signos = signosData.listSignos

        val signoAries = signosData.listSignos.first { it.id == 1 }

        val txt = root.findViewById<TextView>(R.id.txtDescription)
        txt.text = signoAries.desc
        return root
    }
}