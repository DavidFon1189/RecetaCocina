package viewmodel

import data.RecestasResponse
import data.model.GenericResponse
import data.network.RepositoryViewModel
import retrofit2.Call

class RecetasCU() {

    private val repositoryViewModel = RepositoryViewModel()
    suspend operator fun invoke(): Call<GenericResponse<RecestasResponse>>? {
        return  repositoryViewModel.getRecetas()
    }
}