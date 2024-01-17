package com.maxkor.interonnection.ui.screens.list

import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.data.retrofit.DataModel
import com.maxkor.interonnection.navigation.NavigationHelper
import com.maxkor.interonnection.navigation.Screen
import com.maxkor.interonnection.ui.SharedViewModel
import com.maxkor.interonnection.ui.screens.DataCard
import com.maxkor.interonnection.ui.screens.DataModelTest
import com.maxkor.interonnection.ui.screens.DataModelTest.Companion.testList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: SharedViewModel,
    navHelper: NavigationHelper,
) {
    val dataList = remember { viewModel.dataLIst }

    var testTextSearchView by remember { mutableStateOf("") }
    val testList =
        remember { mutableStateOf(com.maxkor.interonnection.ui.screens.DataModelTest.testList) }

    val search: (String) -> Unit = { letters ->
        val filteredList = DataModelTest.testList.filter { model ->
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
            items(dataList.value) { dataModel ->
                val textFieldText = remember { mutableStateOf("") }
                Row(modifier = Modifier.clickable {
                    viewModel.passCurrentElement(dataModel)
                    navHelper.navigateTo(Screen.Detail.route)
                }) {
                    DataCard(
                        dataModel = dataModel,
                        textFieldTextState = textFieldText,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
