package com.maxkor.interonnection.domain.usecases

import com.maxkor.interonnection.domain.DataModel
import com.maxkor.interonnection.domain.MainRepository
import javax.inject.Inject

class GetDataUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(hasInternetConnection: Boolean): List<DataModel> {
        return repository.getData(hasInternetConnection)
    }
}