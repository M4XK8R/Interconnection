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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.navigation.NavigationHelper
import com.maxkor.interonnection.navigation.Screen
import com.maxkor.interonnection.ui.SharedViewModel
import com.maxkor.interonnection.ui.screens.DataCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: SharedViewModel,
    navHelper: NavigationHelper,
) {
//    val dataList = remember { viewModel.dataLIst }
    val dataList = viewModel.dataListReactive.collectAsState(initial = emptyList())
    createLog("dataList = ${dataList.value}")
    var searchedText by remember { viewModel.searchedText }

//    val search: (String) -> Unit = { letters ->
//        viewModel.saveData(viewModel.stableList.value)
//        val filteredList = dataList.value.filter { model ->
//            model.fullName.lowercase().startsWith(letters.lowercase())
//        }
//        viewModel.saveData(filteredList)
//    }

    Column(Modifier.fillMaxSize()) {
        SearchBar(
            query = searchedText,
            onQueryChange = {
                searchedText = it
//                search(it)
            },
            onSearch = {
//                search(searchedText)
                       },
            active = false,
            onActiveChange = {},
            placeholder = { Text(text = "Search...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) { TODO() }

        LazyColumn() {
            items(dataList.value) { dataModel ->
                val textFieldText = remember { mutableStateOf("") }
                Row(modifier = Modifier.clickable {
                    viewModel.passCurrentElement(dataModel)
                    navHelper.navigateTo(Screen.Detail.route)
                }) {
                    DataCard(
                        dataModel = dataModel,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
