package com.maxkor.interonnection.domain.usecases

import com.maxkor.interonnection.domain.DataModel
import com.maxkor.interonnection.domain.MainRepository
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(dataModel: DataModel) {
        val newDataModel = dataModel.copy(
            isFavorite = !dataModel.isFavorite
        )
        repository.insertToDb(newDataModel)
    }
}

