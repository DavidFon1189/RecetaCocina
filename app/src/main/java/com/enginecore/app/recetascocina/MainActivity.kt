package com.enginecore.app.recetascocina

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enginecore.app.recetascocina.adapter.AdapterRecetas
import com.enginecore.app.recetascocina.adapter.ClicRecyclerRecetas
import com.enginecore.app.recetascocina.databinding.MainActivityBinding
import data.RecestasResponse
import data.Receta
import data.model.GenericResponse
import viewmodel.RecetasViewModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), ClicRecyclerRecetas {

    private lateinit var binding: MainActivityBinding
    private val recetasViewModel: RecetasViewModel by viewModels()
    private lateinit var adapterRecetas: AdapterRecetas
    private lateinit var recetasList: ArrayList<Receta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Utils.statusBar(this)
        binding.toolbar.tvTituloToolbar.text = getString(R.string.recetas)
        binding.toolbar.imvToolbar.visibility = View.GONE
        val layoutManagerRecetas: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewRecetas.setHasFixedSize(true)
        binding.recyclerViewRecetas.layoutManager = layoutManagerRecetas
        binding.recyclerViewRecetas.itemAnimator = DefaultItemAnimator()
        recetasViewModel.getRecetas()
        initVieModel()
        recetasList = ArrayList()

        binding.svBuscarRecetas.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(buscarStr: String): Boolean {
                filter(buscarStr)
                return true
            }

        })
    }

    private fun initVieModel() {
        viewModel()
        isLoading()
        onErrorServer()
        onError()
    }

    private fun viewModel() {
        recetasViewModel.getRecetasModelSucces.observe(this, Observer {
            if (it != null) {
                val recetasResponse: GenericResponse<RecestasResponse>? = it.body()
                recetasList = recetasResponse?.respuesta!!.recetas
                adapterRecetas = AdapterRecetas(recetasList, this)
                binding.recyclerViewRecetas.adapter = adapterRecetas
            } else {
                showPopupDialogError("No se encontraron recetas")
            }
        })
    }

    private fun isLoading() {
        recetasViewModel.isLoading.observe(this, Observer {
            if (it) {
                DialogManager.progressBar(this)
            } else {
                DialogManager.progressBar(this).dismissAllowingStateLoss()
            }
        })
    }

    private fun onErrorServer() {
        recetasViewModel.modelErrorServer.observe(this, Observer {
            showPopupDialogError(getString(it))
        })
    }

    private fun onError() {
        recetasViewModel.errorGeneral.observe(this, Observer {
            showPopupDialogError(it)
        })
    }

    private fun showPopupDialogError(subtitulo: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_fragment)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val tvSubtituloDialog = dialog.findViewById<TextView>(R.id.tv_subtitulo_dialog)
        tvSubtituloDialog.text = subtitulo
        val btnAceptar = dialog.findViewById<Button>(R.id.btn_aceptar_dialog)
        dialog.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        btnAceptar.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onClickRecyclerRecetas(recetas: Receta) {
        val intent = Intent(this, DetallesRecetaActivity::class.java)
        intent.putExtra("imagen", recetas.imgReceta)
        intent.putExtra("nombreReceta", recetas.nombreReceta)
        intent.putExtra("pasosReceta", recetas.pasosReceta)
        intent.putExtra("origenReceta", recetas.origenReceta)
        intent.putExtra("latitud", recetas.latitud)
        intent.putExtra("longitud", recetas.longitud)
        startActivity(intent)

    }

    private fun filter(text: String) {
        val filteredlist: ArrayList<Receta> = ArrayList()
        // running a for loop to compare elements.

        for (list in recetasList){
                if (list.nombreReceta.lowercase(Locale.getDefault()).contains(
                        text.lowercase(Locale.getDefault()))
                    || list.pasosReceta.lowercase(Locale.getDefault()).contains(
                        text.lowercase(Locale.getDefault())
                    )){
                    filteredlist.add(list)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
//            Toast.makeText(this, "No se encontraron coincidencias", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapterr class.
            adapterRecetas.filterList(filteredlist)
        }
    }
}