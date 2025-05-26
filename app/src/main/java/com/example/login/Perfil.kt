package com.example.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class Perfil : Fragment() {

    private lateinit var textNombre: TextView
    private lateinit var textExp: TextView
    private lateinit var textFacultad: TextView
    private lateinit var textCarrera: TextView
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val user = FirebaseAuth.getInstance().currentUser
        val correo = user?.email
        val rootView = inflater.inflate(R.layout.fragment_perfil, container, false)
        textNombre = rootView.findViewById(R.id.nombre)
        textExp = rootView.findViewById(R.id.expediente)
        textFacultad = rootView.findViewById(R.id.facultad)
        textCarrera = rootView.findViewById(R.id.carrera)

        obtenerNombreUsuario()
        // Get the button from the layout
        val textV = rootView.findViewById<TextView>(R.id.correo)
        val texto: String = activity?.intent?.getStringExtra("EXTRA_TEXTO").orEmpty()
        textV.text = "$texto"

        val button = rootView.findViewById<Button>(R.id.info)
        button.setOnClickListener {
            Toast.makeText(getContext(), "Versión 1.0 - Desarrollada por: AGJ\nApp desarrolladora de habilidades.", Toast.LENGTH_SHORT).show()
        }

        val buttonback= rootView.findViewById<ImageButton>(R.id.back)
        buttonback.setOnClickListener{
            val intento= Intent(requireContext(), Skillhubmainpage::class.java)
            startActivity(intento)
        }
        val buttoncerrar= rootView.findViewById<Button>(R.id.cerrar)
        buttoncerrar.setOnClickListener{
            val intento= Intent(requireContext(), MainActivity::class.java)
            startActivity(intento)
        }

        val db = FirebaseFirestore.getInstance()

        if (correo != null) {
            db.collection("usuarios")
                .whereEqualTo("email", correo)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val documento = querySnapshot.documents[0]
                        val nombre = documento.getString("nombre")
                        Log.d("Firestore", "Nombre del usuario: $nombre")
                    } else {
                        Log.d("Firestore", "No se encontró usuario con ese correo")
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("Firestore", "Error al buscar usuario", e)
                }
        }


        return rootView
    }

    private fun obtenerNombreUsuario() {
        val correo = auth.currentUser?.email

        if (correo != null) {
            db.collection("users")
                .whereEqualTo("correo", correo)
                .get()
                .addOnSuccessListener { snapshot ->
                    if (!snapshot.isEmpty) {
                        val doc = snapshot.documents[0]
                        val nombre = doc.getString("nombre")
                        val exp = doc.getString("expediente")
                        val facultad = doc.getString("facultad")
                        val carrera = doc.getString("carrera")
                        textNombre.text = "Bienvenido, $nombre"
                        textExp.text = "$exp"
                        textFacultad.text = "$facultad"
                        textCarrera.text = "$carrera"
                    }
                }
        }
    }
}



