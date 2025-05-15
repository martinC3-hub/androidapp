package com.example.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import android.content.Intent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AppCompatActivity



class Perfil : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_perfil, container, false)
        // Get the button from the layout
        val textV = rootView.findViewById<TextView>(R.id.nombre)
        val texto: String = activity?.intent?.getStringExtra("EXTRA_TEXTO").orEmpty()
        textV.text = "Bienvenido $texto"

        return rootView
    }

}

/*
package com.example.login

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class mainpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.skillhubmainpage)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textV = findViewById<TextView>(R.id.mensaje)
        val texto:String = intent.extras?.getString("EXTRA_TEXTO").orEmpty()
        textV.text="Bienvenido $texto"
    }
}

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".mainpage">

    <TextView
        android:id="@+id/mensaje"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Bienvenido Correo"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent">
    </TextView>

</androidx.constraintlayout.widget.ConstraintLayout>


package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var user: AppCompatEditText
    private lateinit var psw: AppCompatEditText
    private lateinit var btnLogin: AppCompatButton
    private lateinit var btnRegister: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //definicion de las variables
        auth = FirebaseAuth.getInstance() //objeto autenticación
        user = findViewById<AppCompatEditText>(R.id.input2)
        psw = findViewById<AppCompatEditText>(R.id.input)
        btnLogin = findViewById<AppCompatButton>(R.id.aceptar)
        btnRegister = findViewById<AppCompatButton>(R.id.registrar)


        val input2 = findViewById<AppCompatEditText>(R.id.input2)
        val input = findViewById<AppCompatEditText>(R.id.input)
        val btnaceptar = findViewById<AppCompatButton>(R.id.aceptar)
        val btnregistrar = findViewById<AppCompatButton>(R.id.registrar)
        val output = findViewById<TextView>(R.id.output)



        fun login(correo: String, contra: String) {
            auth.signInWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                        val intento= Intent(this, mainpage::class.java)
                        intento.putExtra("EXTRA_TEXTO", correo)
                        startActivity(intento)
                    } else {
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        fun registrar(correo: String, contra: String) {
            auth.createUserWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        btnLogin.setOnClickListener{
            val correo = user.text.toString().trim()
            val contra = psw.text.toString().trim()
            if(correo.isNotEmpty() && contra.isNotEmpty()){
                login(correo,contra)
            }else{
                Toast.makeText(this,"Ingresa las credenciales",Toast.LENGTH_SHORT).show()
            }
        }
        btnRegister.setOnClickListener{
            val correo = user.text.toString().trim()
            val contra = psw.text.toString().trim()
            if(correo.isNotEmpty() && contra.isNotEmpty()){
                registrar(correo,contra)
            }else{
                Toast.makeText(this,"Ingresa las credenciales",Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
 */