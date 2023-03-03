package data.network

import data.RecestasResponse
import data.model.GenericResponse
import retrofit2.Call

class RepositoryViewModel {

    private val api = ServicesViewModel()

    suspend fun getRecetas(): Call<GenericResponse<RecestasResponse>>?{
        val response = api.getRecetas()
        return response
    }
}