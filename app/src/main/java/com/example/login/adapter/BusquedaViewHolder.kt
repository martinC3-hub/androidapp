package com.example.login.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login.Busqueda
import com.example.login.R

class BusquedaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val nombre = itemView.findViewById<TextView>(R.id.tvNombre)
    val tipo = itemView.findViewById<TextView>(R.id.tvTipo)
    val imagen = itemView.findViewById<ImageView>(R.id.ivBusqueda)
    val borrar = itemView.findViewById<ImageButton>(R.id.borrar)

    fun render(
        busqueda: Busqueda,
        onClickListener: (Busqueda) -> Unit,
        onClickDelete: (Int) -> Unit
    ){
        nombre.text=busqueda.nombre
        tipo.text=busqueda.tipo
        Glide.with(imagen.context).load(busqueda.imagen).into(imagen)
        itemView.setOnClickListener {onClickListener(busqueda)}
        borrar.setOnClickListener{onClickDelete(adapterPosition)}
    }
}

/*
//public var listaFiltrada: List<Busqueda> = listaOriginal
        val nombre: TextView = itemView.findViewById(android.R.id.text1)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusquedaViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return BusquedaViewHolder(vista)
    }

    override fun onBindViewHolder(holder: BusquedaViewHolder, position: Int) {
        holder.nombre.text = listaFiltrada[position].texto
    }

    override fun getItemCount(): Int = listaFiltrada.size

    fun filtrar(texto: String) {
        listaFiltrada = if (texto.isEmpty()) {
            listaOriginal
        } else {
            listaOriginal.filter {
                it.texto.contains(texto, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    fun actualizarLista(nuevaLista: List<Busqueda>) {
        listaOriginal = nuevaLista
        listaFiltrada = nuevaLista
        notifyDataSetChanged()
    }
*/