package com.example.perros

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity2 : AppCompatActivity() {
    private val db =FirebaseFirestore.getInstance()
    private val tuColeccion=db.collection("Products")
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TuAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        recyclerView= findViewById(R.id.rDatos)
        recyclerView.layoutManager=LinearLayoutManager(this)
        adapter= TuAdapter()
        recyclerView.adapter=adapter

        val btnConsultar : Button = findViewById(R.id.btnConsultar)
        val btnInsertar: Button = findViewById(R.id.btnInsertar)

        btnConsultar.setOnClickListener()
        {
            consultarColeccion()
        }

        btnInsertar.setOnClickListener()
        {
            val db= FirebaseFirestore.getInstance()
            val txt_nombre : TextView = findViewById(R.id.txt_Nombre)
            val txt_precio : TextView = findViewById(R.id.txt_Precio)
            var nom :String = txt_nombre.text.toString()
            var pre : Int = Integer.parseInt(txt_precio.text.toString())
            val data = hashMapOf(
                "Raza" to nom,
                "Precio" to pre)
            db.collection("Products")
                .add(data)
                .addOnSuccessListener { documentReference ->
                        Toast.makeText(this, "Registrado Correctamente", Toast.LENGTH_SHORT).show()
                    consultarColeccion()


                }
                .addOnFailureListener {e ->}
        }


    }

    private fun consultarColeccion()
    {
        tuColeccion.get()
            .addOnSuccessListener { querySnapshot ->
                val listaTuModelo = mutableListOf<Products>()
                for (document in querySnapshot)
                {
                    val raza = document.getString("Raza")
                    val precio = document.getLong("Precio")?.toInt()
                    val img = document.getString("url").toString()
                    val ID = document.id
                    if (raza !=null && precio !=null)
                    {
                        val tuModelo= Products(ID, raza, precio, img)
                        listaTuModelo.add(tuModelo)
                    }
                }


                adapter.setDatos(listaTuModelo)

            }
    }
}