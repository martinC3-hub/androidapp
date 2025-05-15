package com.example.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class Buscar : Fragment() {
    data class Nota(
        val texto: String=""
    )
    class Lista(private var listaOriginal: List<Nota>) :
        RecyclerView.Adapter<Lista.ViewHolder>() {

        private var listaFiltrada: List<Nota> = listaOriginal

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nombre: TextView = itemView.findViewById(android.R.id.text1)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val vista = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(vista)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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

        fun actualizarLista(nuevaLista: List<Nota>) {
            listaOriginal = nuevaLista
            listaFiltrada = nuevaLista
            notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_buscar, container, false)
        val buscar = rootView.findViewById<SearchView>(R.id.buscar)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)

        val adaptador = Lista(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adaptador

        val db = FirebaseFirestore.getInstance()

        // Leer desde Firestore
        db.collection("notas")
            .get()
            .addOnSuccessListener { result ->
                val lista = result.mapNotNull { it.toObject(Nota::class.java) }
                adaptador.actualizarLista(lista)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error al cargar", Toast.LENGTH_SHORT).show()
            }

        // Filtro en tiempo real
        buscar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adaptador.filtrar(newText ?: "")
                return true
            }
        })


        val button = rootView.findViewById<Button>(R.id.descarga)
        // Set the click listener
        button.setOnClickListener {
            // Open the specified URL in a browser
            Toast.makeText(getContext(), "Button volvera en avengers 2", Toast.LENGTH_SHORT).show()
        }

        val buttonback= rootView.findViewById<ImageButton>(R.id.back)
        buttonback.setOnClickListener{
                val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, Explorar())  // Cambia "R.id.container" por el ID del contenedor de fragmentos
                fragmentTransaction.addToBackStack(null)  // Esto permite regresar al fragmento anterior
                fragmentTransaction.commit()
            }

        return rootView
    }
}







