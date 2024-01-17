package com.maxkor.interonnection.ui.screens.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkor.interonnection.ui.screens.DataCard
import com.maxkor.interonnection.ui.screens.DataModel
import com.maxkor.interonnection.ui.screens.ScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
//    screenState: MutableState<ScreenState>,
//    dataModel: MutableState<DataModel> =
) {
    var testTextSearchView by remember { mutableStateOf("") }
    val testList = remember { mutableStateOf(DataModel.testList) }

    val search: (String) -> Unit = { letters ->
        val filteredList = DataModel.testList.filter { model ->
            model.mainText.lowercase().startsWith(letters.lowercase())
        }
        testList.value = filteredList
    }

    Column(Modifier.fillMaxSize()) {
        SearchBar(
            query = testTextSearchView,
            onQueryChange = {
                testTextSearchView = it
                search(it)
            },
            onSearch = { search(testTextSearchView) },
            active = false,
            onActiveChange = {},
            placeholder = { Text(text = "Search...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) { TODO() }

        LazyColumn() {
            items(testList.value) {
                Row(modifier = Modifier.clickable {
//                    dataModel.value = it
//                    screenState.value = ScreenState.DetailState
                }) {
                    DataCard(it)
                }
            }
        }
    }
}

//@Composable
//@Preview(showSystemUi = true)
//fun ListPreview() {
//    ListScreen()
//}