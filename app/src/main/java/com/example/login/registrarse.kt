package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class registrarse : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var psw: AppCompatEditText
    private lateinit var nombre: AppCompatEditText
    private lateinit var correo: AppCompatEditText
    private lateinit var expediente: AppCompatEditText
    private lateinit var carrera: AppCompatEditText
    private lateinit var facultad: AppCompatEditText
    private lateinit var btnRegister: AppCompatButton
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrarse)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance() //objeto autenticación
        nombre = findViewById<AppCompatEditText>(R.id.input1)
        correo = findViewById<AppCompatEditText>(R.id.input2)
        expediente = findViewById<AppCompatEditText>(R.id.input3)
        carrera = findViewById<AppCompatEditText>(R.id.input4)
        facultad = findViewById<AppCompatEditText>(R.id.input5)
        psw = findViewById<AppCompatEditText>(R.id.input)
        btnRegister = findViewById<AppCompatButton>(R.id.registrar)



        fun registrar(correo: String, contra: String,nombre: String,expediente: String,carrera:String,facultad:String) {
            if (nombre.isNotEmpty()) {
                val nota = hashMapOf("nombre" to nombre,
                    "correo" to correo,
                    "expediente" to expediente,
                    "carrera" to carrera,
                    "facultad" to facultad)
                db.collection("users").add(nota)
            }
            auth.createUserWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()


                        val intento= Intent(this, MainActivity::class.java)
                        startActivity(intento)
                    } else {
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        btnRegister.setOnClickListener{
            val correo = correo.text.toString().trim()
            val contra = psw.text.toString().trim()
            val nombre = nombre.text.toString().trim()
            val expediente = expediente.text.toString().trim()
            val carrera = carrera.text.toString().trim()
            val facultad = facultad.text.toString().trim()
            if(correo.isNotEmpty() && contra.isNotEmpty()){
                registrar(correo,contra,nombre,expediente,carrera,facultad)
            }else{
                Toast.makeText(this,"Ingresa las credenciales",Toast.LENGTH_SHORT).show()
            }

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registrar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}