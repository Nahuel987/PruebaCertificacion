package com.example.pruebacertificacion.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pruebacertificacion.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.contenedorFragmentos, FragmentoLista.newInstance("FG", "DESDE ACTIVITY"))
            .commit()
    }
}

