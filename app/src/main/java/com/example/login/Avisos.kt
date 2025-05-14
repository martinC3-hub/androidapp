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
    private lateinit var buttonAdd: ImageButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_avisos, container, false)

        // Botón de retroceso que abre Facebook (?)
        val buttonback = rootView.findViewById<ImageButton>(R.id.back)
        buttonback.setOnClickListener {
            val intento= Intent(requireContext(), Skillhubmainpage::class.java)
            startActivity(intento)
        }
        // Inicializar el botón en el top bar

        // Inicializar el botón en el top bar
        buttonAdd = rootView.findViewById(R.id.add)  // El ID de tu ImageButton en el top bar

        // Configurar el listener para el botón de agregar
        buttonAdd.setOnClickListener {
            // Al hacer clic en el botón, reemplaza el fragmento actual por "Add"
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, Add())  // Cambia "R.id.container" por el ID del contenedor de fragmentos
            fragmentTransaction.addToBackStack(null)  // Esto permite regresar al fragmento anterior
            fragmentTransaction.commit()
        }


        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()

        // Referencia al contenedor en tu layout
        val contenedorAvisos = rootView.findViewById<LinearLayout>(R.id.contenedorAvisos)

// Escuchar cambios en la colección "avisos"
        db.collection("avisos").addSnapshotListener { snapshots, _ ->
            contenedorAvisos.removeAllViews()

            snapshots?.forEach { document ->
                val avisoView = LayoutInflater.from(requireContext())
                    .inflate(R.layout.item_aviso, contenedorAvisos, false)

                avisoView.findViewById<TextView>(R.id.titulo).text = document.getString("titulo") ?: "Sin título"
                avisoView.findViewById<TextView>(R.id.tipo).text = document.getString("tipo") ?: "Sin tipo"
                avisoView.findViewById<TextView>(R.id.facultad).text = document.getString("facultad") ?: "Sin facultad"
                avisoView.findViewById<TextView>(R.id.descripcion).text = document.getString("descripcion") ?: ""
                avisoView.findViewById<TextView>(R.id.contacto).text = document.getString("contacto") ?: "Sin contacto"



                contenedorAvisos.addView(avisoView)
            }
        }




        return rootView
    }
}
