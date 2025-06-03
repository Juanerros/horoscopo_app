package com.example.horoscopoxml.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopoxml.R
import com.example.horoscopoxml.data.SignosAdapter
import com.google.android.material.button.MaterialButton

class ThirdFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_third, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerSignos)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = SignosAdapter.SignosAdapter()

        return root
    }
}