package com.maxkor.interonnection.domain.usecases

import com.maxkor.interonnection.domain.models.DataModel
import com.maxkor.interonnection.domain.repository.MainRepository
import javax.inject.Inject

class GetElementUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(modelId: Int): DataModel {
        return repository.getElement(modelId)
    }
}