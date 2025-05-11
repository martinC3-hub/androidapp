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

class Add : Fragment() {
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_add, container, false)

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()

        // Vincular elementos
        val titulo = rootView.findViewById<EditText>(R.id.tituloInput)
        val descripcion = rootView.findViewById<EditText>(R.id.cuerpoInput)
        val btn = rootView.findViewById<Button>(R.id.btnGuardar)
        val spinnerfacultad: Spinner = rootView.findViewById(R.id.spinner_flairs)
        val spinnertipo: Spinner = rootView.findViewById(R.id.spinner_etiqueta)

        // Adapter para spinner de facultades
        val adapterFacultad = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.facultades_uaq,
            android.R.layout.simple_spinner_item
        )
        adapterFacultad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerfacultad.adapter = adapterFacultad

        // Adapter para spinner de tipos
        val adapterTipo = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.tipos_aviso,
            android.R.layout.simple_spinner_item
        )
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnertipo.adapter = adapterTipo

        val buttonback = rootView.findViewById<ImageButton>(R.id.back)
        buttonback.setOnClickListener {
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, Avisos())  // Cambia "R.id.container" por el ID del contenedor de fragmentos
            fragmentTransaction.addToBackStack(null)  // Esto permite regresar al fragmento anterior
            fragmentTransaction.commit()
        }
        // Guardar en Firestore
        btn.setOnClickListener {
            val texto = titulo.text.toString()
            val desc = descripcion.text.toString()
            val selectedFaculty = spinnerfacultad.selectedItem?.toString() ?: "Todas las Facultades"
            val selectedTipo = spinnertipo.selectedItem?.toString() ?: "Sin tipo"

            if (texto.isNotEmpty() && desc.isNotEmpty()) {
                val aviso = hashMapOf(
                    "titulo" to texto,
                    "descripcion" to desc,
                    "facultad" to selectedFaculty,
                    "tipo" to selectedTipo
                )

                db.collection("avisos").add(aviso)
                titulo.text.clear()
                descripcion.text.clear()
            }
        }

        return rootView
    }
}
