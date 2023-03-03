package data.network

import data.RecestasResponse
import data.api.RetrofitClient
import data.model.GenericResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class ServicesViewModel {

    private val retrofit = RetrofitClient.provideAPIService()

    suspend fun getRecetas(): Call<GenericResponse<RecestasResponse>>? {
        return withContext(Dispatchers.IO) {
            val response = retrofit?.getRecetas()
            response
        }
    }
}