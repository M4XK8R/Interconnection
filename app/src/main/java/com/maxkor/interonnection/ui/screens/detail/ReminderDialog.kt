package com.maxkor.interonnection.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderDialog(
    openDialog: MutableState<Boolean>
) {
    val radioOptions = listOf("15 min", "1 hour", "1 day", "7 days")

    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(radioOptions[0])
    }

    AlertDialog(onDismissRequest = {}) {
        Column(
            modifier = Modifier
//                .fillMaxSize()
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choose the date",
                modifier = Modifier.padding(top = 16.dp),
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineMedium,
            )

            Spacer(modifier = Modifier.size(10.dp))

            Column(Modifier.selectableGroup()) {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = null // null recommended for accessibility with screenreaders
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val context = LocalContext.current
                Button(
                    onClick = {
                        NotificationHelper.showNotification(context)
                        openDialog.value = false
                    },
                    modifier = Modifier
//                    .align(Alignment.BottomStart)
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Confirm",
                        fontSize = TextUnit(14f, TextUnitType.Sp),
                        color = MaterialTheme.colorScheme.background,
                    )
                }

                Button(
                    onClick = { openDialog.value = false },
                    modifier = Modifier
//                    .align(Alignment.BottomEnd)
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = TextUnit(14f, TextUnitType.Sp),
                        color = MaterialTheme.colorScheme.background,
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ReminderDialogPreview() {
//    ReminderDialog()
}