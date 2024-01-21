package com.maxkor.interonnection.domain.usecases

import com.maxkor.interonnection.domain.models.DataModel
import com.maxkor.interonnection.domain.repository.MainRepository
import javax.inject.Inject

class RemoveFromFavoritesUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(dataModel: DataModel) {
        val newDataModel = dataModel.copy(
            extraText = DataModel.DEFAULT_EXTRA_TEXT,
            isFavorite = !dataModel.isFavorite
        )
        repository.insertToDb(newDataModel)
    }
}