package com.enginecore.app.recetascocina.adapter

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.enginecore.app.recetascocina.R
import data.RecestasResponse
import data.Receta

class AdapterRecetas(
    private var recetasList: ArrayList<Receta>,
    private var onClickRecicler: ClicRecyclerRecetas
) : RecyclerView.Adapter<AdapterRecetas.RecetaViewHolder>() {

    class RecetaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var cardView: CardView
        var nombreReceta: TextView = itemView.findViewById(R.id.tv_nombre_receta)
        var descripcionReceta: TextView = itemView.findViewById(R.id.tv_decripcion_receta)
        var imgView: ImageView
        var masDetalles: TextView = itemView.findViewById(R.id.tv_mas_detalles)
        var onClickRecicler: ClicRecyclerRecetas? = null
        var detalleReceta: Receta? = null

        init {
            imgView = itemView.findViewById(R.id.imv_receta)
            cardView = itemView.findViewById(R.id.carv_view_recetas)
            cardView.setOnClickListener(this)
            masDetalles.setOnClickListener(this)
        }

        fun bind(recetasResponse: Receta, onClickRecicler: ClicRecyclerRecetas) {
            this.onClickRecicler = onClickRecicler
             detalleReceta = recetasResponse
            nombreReceta.text = recetasResponse.nombreReceta
            descripcionReceta.text = recetasResponse.pasosReceta
            val decodeString = Base64.decode(recetasResponse.imgReceta, Base64.DEFAULT)
            val imagenBitmap = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.size)
            imgView.setImageResource(R.drawable.logo_recetario)
            if (imagenBitmap != null) {
                imgView.setImageBitmap(imagenBitmap)
            }

            Log.d("TAG", "decodeString" + recetasResponse.imgReceta)
            Log.d("TAG", "decodeString" + decodeString.toString())
            Log.d("TAG", "imagenBitmap" + imagenBitmap)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.tv_mas_detalles -> {
                    detalleReceta?.let { onClickRecicler?.onClickRecyclerRecetas(it) }
                }
            }
        }
    }

    fun filterList(filterllist: ArrayList<Receta>) {
        // below line is to add our filtered
        // list in our course array list.
        recetasList = filterllist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_recetas, parent, false)
        return RecetaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        val recetasList = recetasList[position]
        holder.cardView.setTag(position)
        val onClickRecicler: ClicRecyclerRecetas = onClickRecicler
        holder.bind(recetasList, onClickRecicler)
    }

    override fun getItemCount(): Int {
        return recetasList.size
    }
}