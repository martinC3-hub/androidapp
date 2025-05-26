package com.example.login

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
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
        val boton = findViewById<Button>(R.id.registrar)
        boton.paintFlags = boton.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        fun login(correo: String, contra: String) {
            auth.signInWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                        val intento= Intent(this, Skillhubmainpage::class.java)
                        intento.putExtra("EXTRA_TEXTO", correo)
                        startActivity(intento)
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
            val intento= Intent(this, registrarse::class.java)
            startActivity(intento)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}