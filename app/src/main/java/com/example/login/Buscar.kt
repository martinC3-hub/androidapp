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

class Buscar : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_buscar, container, false)

        // Get the button from the layout
        val button = rootView.findViewById<Button>(R.id.descarga)

        // Set the click listener
        button.setOnClickListener {
            // Open the specified URL in a browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sence.gob.cl/sites/default/files/elabor1.pdf"))
            startActivity(intent)
        }

        val buttonback= rootView.findViewById<ImageButton>(R.id.back)
        buttonback.setOnClickListener{
            val intent=Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com"))
            startActivity(intent)
        }

        return rootView
    }
}
