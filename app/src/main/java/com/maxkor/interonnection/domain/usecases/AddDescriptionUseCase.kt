package com.maxkor.interonnection.domain.usecases

import com.maxkor.interonnection.domain.DataModel
import com.maxkor.interonnection.domain.MainRepository
import javax.inject.Inject

class AddDescriptionUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(dataModel: DataModel, text: String) {
        val newDataModel = dataModel.copy(
            extraText = text
        )
        repository.insertToDb(newDataModel)
    }
}