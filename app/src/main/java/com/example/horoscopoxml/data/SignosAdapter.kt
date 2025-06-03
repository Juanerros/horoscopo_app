package com.example.horoscopoxml.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopoxml.R

class SignosAdapter {
    class SignosAdapter : RecyclerView.Adapter<SignosAdapter.SignoViewHolder>() {

        class SignoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imagen: ImageView = view.findViewById(R.id.imgSigno)
            val info: TextView = view.findViewById(R.id.txtInfo)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_signo, parent, false)
            return SignoViewHolder(view)
        }

        override fun onBindViewHolder(holder: SignoViewHolder, position: Int) {
            val signo = signosData.listSignos[position]
            holder.imagen.setImageResource(signo.imgId)
            holder.info.text = "${signo.name}\n${signo.date}\n\n${signo.desc}"
        }

        override fun getItemCount() = signosData.listSignos.size
    }
}