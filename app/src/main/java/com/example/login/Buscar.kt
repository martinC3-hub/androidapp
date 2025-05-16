package com.example.login

import android.content.Intent
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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.adapter.BusquedaAdapter
import com.google.firebase.firestore.FirebaseFirestore


class Buscar : Fragment() {

    private lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_buscar, container, false)
        val buscar = rootView.findViewById<SearchView>(R.id.buscar)

        initReclyclerView()
        val button = rootView.findViewById<Button>(R.id.descarga)
        // Set the click listener
        button.setOnClickListener {
            // Open the specified URL in a browser
            Toast.makeText(getContext(), "Button volvera en avengers 2", Toast.LENGTH_SHORT).show()
        }

        val buttonback= rootView.findViewById<ImageButton>(R.id.back)
        buttonback.setOnClickListener{
            val intento= Intent(requireContext(), Skillhubmainpage::class.java)
            startActivity(intento)
            }


        return rootView
    }

    private fun initReclyclerView(){
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.lista)
        val manager=LinearLayoutManager(requireContext())
        //val decoration=DividerItemDecoration(requireContext(), manager.orientation)
        recyclerView.layoutManager= manager
        recyclerView.adapter=
            BusquedaAdapter(BusquedaProvider.busquedaFirst) { nombre ->
                onItemSelected(
                    nombre
                )
        }
        //recyclerView.addItemDecoration(decoration)
    }

    fun onItemSelected(busqueda: Busqueda) {
        //Toast.makeText(requireContext(), busqueda.nombre, Toast.LENGTH_SHORT).show()

            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, Documentos())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }


/*
val adaptador = BusquedaAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adaptador

        val db = FirebaseFirestore.getInstance()

        db.collection("notas")
            .get()
            .addOnSuccessListener { result ->
                val lista = result.mapNotNull { it.toObject(Busqueda::class.java) }
                adaptador.actualizarLista(lista)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error al cargar", Toast.LENGTH_SHORT).show()
            }

        buscar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adaptador.filtrar(newText ?: "")
                return true
            }
        })

 */







