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
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.auth
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var  firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btningresar : Button = findViewById(R.id.btnIngresar)
        val txtemail : TextView = findViewById(R.id.edtEmail)
        val txtpass : TextView = findViewById(R.id.edtPassword)
        val btnCrear_CuentaNueva : TextView = findViewById(R.id.btnCrearCuenta)
        val btnRecordar : TextView = findViewById(R.id.btnOlvidar)
        firebaseAuth= Firebase.auth
        btningresar.setOnClickListener()
        {
            sigIn(txtemail.text.toString(),txtpass.text.toString())
        }
        btnCrear_CuentaNueva.setOnClickListener()
        {
            val i = Intent(this, CrearCuentaActivity::class.java)
            startActivity(i)
        }
        btnRecordar.setOnClickListener()
        {
            val i = Intent(this, RecordarPassActivity::class.java)
            startActivity(i)
        }
    }

    private fun sigIn(email: String, password: String)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task->
                if (task.isSuccessful){
                    val  user = firebaseAuth.currentUser
                    Toast.makeText(baseContext,"Autenticación Exitosa",Toast.LENGTH_SHORT).show()
                    //SEGUNDA ACTIVIDAD
                    val i = Intent( this, MainActivity2::class.java)
                    startActivity(i)
                }
                else
                {
                    Toast.makeText(baseContext,"Error de Email y/o Contraseña",Toast.LENGTH_SHORT).show()
                }
            }
    }
}