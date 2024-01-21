package com.maxkor.interonnection.domain.usecases

import com.maxkor.interonnection.domain.repository.MainRepository
import javax.inject.Inject

class LoadDataFromServerToDbUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(hasInternetConnection: Boolean) {
        repository.loadDataFromServerToDb(hasInternetConnection)
    }
}
