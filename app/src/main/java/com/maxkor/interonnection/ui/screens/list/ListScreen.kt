package com.maxkor.interonnection.ui.screens.list

import android.annotation.SuppressLint
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkor.interonnection.navigation.NavigationHelper
import com.maxkor.interonnection.ui.screens.DataCard

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navHelper: NavigationHelper,
    viewModel: ListViewModel = hiltViewModel()
) {
    var searchedText by remember { viewModel.searchedText }

    val dataList = viewModel.dataListReactive.collectAsState(
        initial = emptyList()
    ).value.filter { model ->
        model.fullName.lowercase().startsWith(searchedText.lowercase())
    }

    Column(Modifier.fillMaxSize()) {
        SearchBar(
            query = searchedText,
            onQueryChange = { searchedText = it },
            onSearch = {},
            active = false,
            onActiveChange = {},
            placeholder = { Text(text = "Search...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            content = {}
        )

        LazyColumn() {
            items(dataList) { dataModel ->
                Row(modifier = Modifier.clickable {
                    navHelper.navigateToDetail(dataModel.id.toString())
                }) {
                    DataCard(
                        dataModel = dataModel,
                        addToFavorites = { viewModel.addToFavorites(it) },
                        removeFromFavorites = { viewModel.removeFromFavorites(it) },
                        addDescription = { dataModel, text ->
                            viewModel.addDescription(dataModel, text)
                        }
                    )
                }
            }
        }
    }
}
