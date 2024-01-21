package com.maxkor.interonnection.domain.models

data class DataModel(
    val id: Int,
    val fullName: String,
    val price: String,
    val imageUrl: String,
    var extraText: String,
    var isFavorite: Boolean
) {
    companion object {
        val initial = DataModel(
            0,
            "",
            "",
            "",
            "",
            false
        )

        const val DEFAULT_EXTRA_TEXT = ""
    }
}
