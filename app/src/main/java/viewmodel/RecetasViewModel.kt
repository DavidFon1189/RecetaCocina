package viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enginecore.app.recetascocina.R
import data.RecestasResponse
import data.model.GenericResponse
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecetasViewModel: ViewModel() {
    val getRecetasModelSucces = MutableLiveData<Response<GenericResponse<RecestasResponse>>?>()
    val modelErrorServer = MutableLiveData<Int>()
    val isLoading = MutableLiveData<Boolean>()
    val errorGeneral = MutableLiveData<String>()

    fun getRecetas() {
        isLoading.postValue(true)
        val recetasCU = RecetasCU()
        viewModelScope.launch {
            val result: Call<GenericResponse<RecestasResponse>>? = recetasCU()
            result?.enqueue(object : Callback<GenericResponse<RecestasResponse>> {
                override fun onResponse(
                    call: Call<GenericResponse<RecestasResponse>>,
                    response: Response<GenericResponse<RecestasResponse>>
                ) {
                    if (response.isSuccessful) {
                        getRecetasModelSucces.postValue(response)
                        isLoading.postValue(false)
                    } else {
                        isLoading.postValue(false)
                        val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                        val errorCode = jsonObj.getString("codigo")
                        val mensaje = jsonObj.getString("mensaje")
                        errorGeneral.postValue("$errorCode $mensaje")

                    }
                }

                override fun onFailure(call: Call<GenericResponse<RecestasResponse>>, t: Throwable) {
                    modelErrorServer.postValue(R.string.error_de_comunicacion_servidor)
                    isLoading.postValue(false)
                }
            })
        }
    }
}