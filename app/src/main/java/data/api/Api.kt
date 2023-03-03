package data.api

import data.RecestasResponse
import data.api.EndPiont.GET_RECETAS
import data.model.GenericResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET(GET_RECETAS)
    fun getRecetas(): Call<GenericResponse<RecestasResponse>>
}