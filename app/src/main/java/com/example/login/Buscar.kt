package com.example.login

import android.content.Intent
import android.icu.text.Transliterator.Position
import android.net.Uri
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
    private var buscarMutableList: MutableList<Busqueda> = mutableListOf()
    private lateinit var adapter: BusquedaAdapter
    private var listaOriginal: MutableList<Busqueda> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_buscar, container, false)
        val buscar = rootView.findViewById<SearchView>(R.id.buscar)

        initRecyclerView()

        val buttonback = rootView.findViewById<ImageButton>(R.id.back)
        buttonback.setOnClickListener {
            val intento = Intent(requireContext(), Skillhubmainpage::class.java)
            startActivity(intento)
        }

        val buttonAdd = rootView.findViewById<ImageButton>(R.id.add)
        buttonAdd.setOnClickListener {
            // Al hacer clic en el botón, reemplaza el fragmento actual por "Add"
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, Add2())  // Cambia "R.id.container" por el ID del contenedor de fragmentos
            fragmentTransaction.addToBackStack(null)  // Esto permite regresar al fragmento anterior
            fragmentTransaction.commit()
        }


        buscar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarBusqueda(newText.orEmpty())
                return true
            }
        })

        return rootView
    }

    private fun initRecyclerView() {
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.lista)
        val manager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = manager

        BusquedaProvider.cargarBusquedasF(
            onComplete = { listaDesdeFirestore ->
                listaOriginal.clear()
                listaOriginal.addAll(listaDesdeFirestore)

                buscarMutableList.clear()
                buscarMutableList.addAll(listaDesdeFirestore)

                adapter = BusquedaAdapter(
                    listaOriginal = buscarMutableList,
                    onClickListener = { nombre -> onItemSelected(nombre) },
                    onClickDelete = { position -> onDeleteItem(position) }
                )

                recyclerView.adapter = adapter
            },
            onError = { exception ->
                Toast.makeText(requireContext(), "Error al cargar: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun onDeleteItem(position: Int){
        buscarMutableList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    private fun filtrarBusqueda(texto: String) {
        val listaFiltrada = listaOriginal.filter {
            it.nombre.contains(texto, ignoreCase = true) || it.tipo.contains(texto, ignoreCase = true)
        }
        buscarMutableList.clear()
        buscarMutableList.addAll(listaFiltrada)
        adapter.notifyDataSetChanged()
    }

    private fun onItemSelected(busqueda: Busqueda) {
        //Toast.makeText(requireContext(), busqueda.nombre, Toast.LENGTH_SHORT).show()

            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(busqueda.url))
            //fragmentTransaction.replace(R.id.container, Documentos())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            startActivity(intent)
        }
    }










