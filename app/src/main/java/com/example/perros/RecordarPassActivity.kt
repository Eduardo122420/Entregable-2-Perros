package com.example.perros

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RecordarPassActivity : AppCompatActivity() {
    private lateinit var  firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordar_pass)
        val txtmail: TextView = findViewById(R.id.txtEmailCambio)
        val btnCambiar: Button = findViewById(R.id.btnCambiar)
        btnCambiar.setOnClickListener()
        {
            sendPasswordReset(txtmail.text.toString())
        }

        firebaseAuth= Firebase.auth
    }

    private fun sendPasswordReset (email: String)
    {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if(task.isSuccessful)
                {
                    Toast.makeText(baseContext,"Correo Enviado", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(baseContext,"Correo Invalido", Toast.LENGTH_SHORT).show()
                }
            }
    }
}


