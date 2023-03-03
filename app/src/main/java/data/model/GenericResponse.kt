package data.model

data class GenericResponse<T>(
    val estatus: Boolean,
    val codigo: String,
    val mensaje: String,
    val respuesta: T
)
