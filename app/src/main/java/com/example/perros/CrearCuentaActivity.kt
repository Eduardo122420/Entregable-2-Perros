package com.example.perros

import android.content.Intent
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

class CrearCuentaActivity : AppCompatActivity() {
    private lateinit var  firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        val txtnombre_nuevo : TextView = findViewById(R.id.edtNombre)
        val txtemail_nuevo : TextView = findViewById(R.id.edtEmailNuevo)
        val txtpassword1 : TextView = findViewById(R.id.edtPasswordNuevo1)
        val txtpassword2 : TextView = findViewById(R.id.edtPasswordNuevo2)
        val btnCrear : Button = findViewById(R.id.btnCrearCuentaNueva)
        val btnRegreso_login : Button = findViewById(R.id.btnRegresarLogin)
        btnRegreso_login.setOnClickListener()
        {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        btnCrear.setOnClickListener()
        {
            var pass1 = txtpassword1.text.toString()
            var pass2 = txtpassword2.text.toString()
            if (pass1.equals(pass2))
            {
                createAccount(txtemail_nuevo.text.toString(),txtpassword1.text.toString())
            }
            else
            {
                Toast.makeText(baseContext,"Contraseñas no Coinciden", Toast.LENGTH_SHORT).show()
                txtpassword1.requestFocus()
            }

        }

        firebaseAuth= Firebase.auth
    }

    private fun createAccount(email: String, password: String)
    {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful)
                {
                    Toast.makeText(baseContext,"Cuenta Creada Correctamente",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(baseContext,"Algo Salió Mal, Error: " + task.exception, Toast.LENGTH_SHORT).show()
                }
    }
}
    private fun sendEmailVerification()
    {
        val user = firebaseAuth.currentUser!!
        user.sendEmailVerification().addOnCompleteListener(this) { task ->

        }
    }
}