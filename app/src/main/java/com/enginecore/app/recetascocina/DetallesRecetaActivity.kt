package com.enginecore.app.recetascocina

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.enginecore.app.recetascocina.databinding.DetallesRecetaActivityBinding

class DetallesRecetaActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var latitud: String
    private lateinit var longitud: String
    private lateinit var binding: DetallesRecetaActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetallesRecetaActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toolbar.tvTituloToolbar.text = getString(R.string.detalle_de_recetas)
        binding.toolbar.imvToolbar.setOnClickListener(this)
        val imagen = intent.getStringExtra("imagen")
        val nombreReceta = intent.getStringExtra("nombreReceta")
        val pasosReceta = intent.getStringExtra("pasosReceta")
        val origenReceta = intent.getStringExtra("origenReceta")
        latitud = intent.getStringExtra("latitud").toString()
        longitud = intent.getStringExtra("longitud").toString()
        val origenRecetaDesc = "Origen de la receta: $origenReceta"
        binding.tvOrigenReceta.text = origenRecetaDesc
        binding.imgRecetaDetalles.setImageResource(R.drawable.logo_recetario)
        if (imagen != null) {
            val decodeString = Base64.decode(imagen, Base64.DEFAULT)
            val imagenBitmap = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.size)
            if (imagenBitmap != null) {
                binding.imgRecetaDetalles.setImageBitmap(imagenBitmap)
            }
        }


        binding.tvNombreReceta.text = nombreReceta
        binding.tvDetalleReceta.text = pasosReceta
        binding.tvMasDetalles.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_mas_detalles -> {
                val intent = Intent(this, MapaOrigenActivity::class.java)
                intent.putExtra("latitud", latitud)
                intent.putExtra("longitud", longitud)
                startActivity(intent)
            }
            R.id.imv_toolbar -> {
                finish()
            }
        }
    }
}