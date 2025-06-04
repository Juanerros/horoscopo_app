package com.example.horoscopoxml.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
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
            findNavController().navigate(R.id.action_global_secondFragment)
        }

// En tu Activity/Fragment
        val imageView: ImageView = root.findViewById(R.id.imgGif)

// Cargar GIF desde drawable
        Glide.with(this)
            .asGif()
            .load(R.drawable.astral)
            .into(imageView)

        return root
    }
}