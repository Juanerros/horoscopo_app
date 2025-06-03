package com.example.horoscopoxml.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.horoscopoxml.R
import com.google.android.material.button.MaterialButton

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_first, container, false)

        val btnNav = root.findViewById<MaterialButton>(R.id.btnNav)

        btnNav.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
        return root

    }
}