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

class Add2 : Fragment() {
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_add2, container, false)

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()

        // Vincular elementos
        val titulo = rootView.findViewById<EditText>(R.id.nombre)
        val imagen = rootView.findViewById<EditText>(R.id.imgUrl)
        val link = rootView.findViewById<EditText>(R.id.linkUrl)
        val btn = rootView.findViewById<Button>(R.id.btnGuardar)
        val spinnertipo: Spinner = rootView.findViewById(R.id.tipo)

        // Adapter para spinner de facultades
        val adapterTipo = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.tipos,
            android.R.layout.simple_spinner_item
        )
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnertipo.adapter = adapterTipo

        val buttonback = rootView.findViewById<ImageButton>(R.id.back)
        buttonback.setOnClickListener {
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, Buscar())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        // Guardar en Firestore
        btn.setOnClickListener {
            val texto = titulo.text.toString()
            val img = imagen.text.toString()
            val web = link.text.toString()
            val selectedTipo = spinnertipo.selectedItem?.toString() ?: "Tipos"

            if (texto.isNotEmpty() && img.isNotEmpty()) {
                val recurso = hashMapOf(
                    "nombre" to texto,
                    "imagen" to img,
                    "url" to web,
                    "tipo" to selectedTipo,
                )

                db.collection("busquedas").add(recurso)
                titulo.text.clear()
                imagen.text.clear()
                link.text.clear()
                Toast.makeText(requireContext(), "Recurso guardado con éxito", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(), "Recurso NO GUARDADO, Completa todos los campos!", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }
}
