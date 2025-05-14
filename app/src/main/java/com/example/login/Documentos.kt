package com.example.login

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Intent
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class Documentos : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_documentos, container, false)

        // Get the button from the layout
        val button = rootView.findViewById<Button>(R.id.descarga)

        // Set the click listener
        button.setOnClickListener {
            // Open the specified URL in a browser
            Toast.makeText(getContext(), "Button volvera en avengers 2", Toast.LENGTH_SHORT).show()
        }

        val buttonback= rootView.findViewById<ImageButton>(R.id.back)
        buttonback.setOnClickListener{
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, Avisos())  // Cambia "R.id.container" por el ID del contenedor de fragmentos
            fragmentTransaction.addToBackStack(null)  // Esto permite regresar al fragmento anterior
            fragmentTransaction.commit()
        }

        return rootView
    }
}
