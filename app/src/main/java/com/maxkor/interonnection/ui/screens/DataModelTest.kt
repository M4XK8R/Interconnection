package com.maxkor.interonnection.ui.screens

import com.maxkor.interonnection.R
import kotlin.random.Random

data class DataModelTest(
    val id: Int = 0,
    val imageResId: Int = R.drawable.test,
    val mainText: String = "Main text $id",
    val extraText: String = "Lorem ipsum...",
    var isFavorite: Boolean = Random.nextBoolean()
) {
    companion object {
        val testModel = DataModelTest()
        val testList = List(20) { DataModelTest(id = it) }
    }
}
