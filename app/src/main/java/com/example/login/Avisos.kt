package com.example.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore

class Avisos : Fragment() {
    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_avisos, container, false)

        // Botón para abrir PDF
        val button = rootView.findViewById<Button>(R.id.descarga)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sence.gob.cl/sites/default/files/elabor1.pdf"))
            startActivity(intent)
        }

        // Botón de retroceso que abre Facebook (?)
        val buttonback = rootView.findViewById<ImageButton>(R.id.back)
        buttonback.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com"))
            startActivity(intent)
        }

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()

        // Vincular elementos
        val input = rootView.findViewById<EditText>(R.id.notaInput)
        val btn = rootView.findViewById<Button>(R.id.btnGuardar)
        val listView = rootView.findViewById<ListView>(R.id.listaNotas)

        // Configurar adaptador
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        // Guardar nota en Firestore
        btn.setOnClickListener {
            val texto = input.text.toString()
            if (texto.isNotEmpty()) {
                val nota = hashMapOf("texto" to texto)
                db.collection("notas").add(nota)
                input.text.clear()
            }
        }

        // Escuchar cambios en Firestore
        db.collection("notas").addSnapshotListener { snapshots, _ ->
            val lista = mutableListOf<String>()
            snapshots?.forEach {
                lista.add(it.getString("texto") ?: "")
            }
            adapter.clear()
            adapter.addAll(lista)
        }

        return rootView
    }
}
