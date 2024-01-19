package com.maxkor.interonnection.data

import com.maxkor.interonnection.data.db.DataEntity
import com.maxkor.interonnection.data.retrofit.DataModelDto
import com.maxkor.interonnection.domain.DataModel

object PojoMapper {

    fun entityToModel(entity: DataEntity): DataModel {
        return DataModel(
            id = entity.id,
            fullName = entity.fullName,
            imageUrl = entity.imageUrl,
            extraText = entity.extraText,
            isFavorite = entity.isFavorite
        )
    }

    fun modelToEntity(model: DataModel): DataEntity {
        return DataEntity(
            id = model.id,
            fullName = model.fullName,
            imageUrl = model.imageUrl,
            extraText = model.extraText,
            isFavorite = model.isFavorite
        )
    }

    fun dtoToModel(dto: DataModelDto): DataModel {
        return DataModel(
            id = dto.id,
            fullName = dto.fullName,
            imageUrl = dto.imageUrl,
            extraText = dto.extraText,
            isFavorite = dto.isFavorite
        )
    }

    fun dtoToEntity(dto: DataModelDto): DataEntity {
        return DataEntity(
            id = dto.id,
            fullName = dto.fullName,
            imageUrl = dto.imageUrl,
            extraText = dto.extraText,
            isFavorite = dto.isFavorite
        )
    }
}