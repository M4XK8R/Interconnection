package com.maxkor.interonnection.domain.usecases

import com.maxkor.interonnection.domain.DataModel
import com.maxkor.interonnection.domain.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataReactiveUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(): Flow<List<DataModel>> {
        return repository.getDataReactive()
    }
}