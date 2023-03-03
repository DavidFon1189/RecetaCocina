package data

data class RecestasResponse(
    val recetas: ArrayList<Receta>
)

data class Receta (
    val nombreReceta: String,
    val pasosReceta: String,
    val imgReceta: String,
    val origenReceta: String,
    val latitud: String,
    val longitud: String
        )