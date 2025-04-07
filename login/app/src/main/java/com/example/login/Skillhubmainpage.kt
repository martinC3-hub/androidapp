package com.example.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment

class Skillhubmainpage : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var explorar: Explorar
    private lateinit var buscar: Buscar
    private lateinit var guardados: Guardados
    private lateinit var perfil: Perfil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skillhubmainpage)

        // Inicializar los fragmentos correctamente
        explorar = Explorar()
        buscar = Buscar()  // Suponiendo que tienes un fragmento "Buscar"
        guardados = Guardados()  // Suponiendo que tienes un fragmento "Guardados"
        perfil = Perfil()  // Suponiendo que tienes un fragmento "Perfil"

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        // Cargar el fragmento inicial al abrir la aplicación
        if (savedInstanceState == null) {
            loadFragment(explorar) // Cargar el fragmento de inicio
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.explorar -> loadFragment(explorar)
                R.id.buscar -> loadFragment(buscar)
                R.id.guardados -> loadFragment(guardados)
                R.id.perfil -> loadFragment(perfil)
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        // Reemplazar el fragmento en el contenedor
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
        return true
    }
}
