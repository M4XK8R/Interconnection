package com.maxkor.interonnection.ui.screens.detail

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.maxkor.interonnection.createLog

private const val FIFTEEN_MIN_IN_MILLIS = 900_000L
private const val ONE_HOUR_IN_MILLIS = 3_600_000L
private const val ONE_DAY_IN_MILLIS = 86_400_000L
private const val SEVEN_DAYS_IN_MILLIS = 604_800_000L

private const val FIFTEEN_MIN = "15 min"
private const val ONE_HOUR = "1 hour"
private const val ONE_DAY = "1 day"
private const val SEVEN_DAYS = "7 days"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderDialog(
    openDialog: MutableState<Boolean>,
    name: String,
    createAlarm: (time: Long, name: String) -> Unit,
    showNotification: (notyText: String) -> Unit,
    checkPermission: (
        launcher: ManagedActivityResultLauncher<String, Boolean>,
        noPermissionCase: () -> Unit,
        defaultCase: () -> Unit,
    ) -> Unit
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
//               TODO()
            } else {
//                TODO()
//                exitProcess(0)
            }
        }
    )

    val radioOptions = listOf(
        FIFTEEN_MIN,
        ONE_HOUR,
        ONE_DAY,
        SEVEN_DAYS
    )

    val defaultTimeValue = FIFTEEN_MIN_IN_MILLIS
    var time by remember { mutableLongStateOf(defaultTimeValue) }
    createLog("time = $time")

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
                text = "Choose time",
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
                                onClick = {
                                    onOptionSelected(text)
                                    when (text) {
                                        FIFTEEN_MIN -> time = FIFTEEN_MIN_IN_MILLIS
                                        ONE_HOUR -> time = ONE_HOUR_IN_MILLIS
                                        ONE_DAY -> time = ONE_DAY_IN_MILLIS
                                        SEVEN_DAYS -> time = SEVEN_DAYS_IN_MILLIS
                                    }
                                    createLog("time = $time")
                                },
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
                        checkPermission.invoke(
                            launcher,
                            {
//                                TODO()
                            },
                            {
//                              TODO()
                            }
                        )
                        val requiredTime = when (time) {
                            FIFTEEN_MIN_IN_MILLIS -> FIFTEEN_MIN
                            ONE_HOUR_IN_MILLIS -> ONE_HOUR
                            ONE_DAY_IN_MILLIS -> ONE_DAY
                            SEVEN_DAYS_IN_MILLIS -> SEVEN_DAYS
                            else -> throw Exception("Unknown time. Smth went wrong")
                        }
                        val notyText = "You  will be notified about $name in $requiredTime"
//                        AlarmHelperImpl.createAlarm(context, time, characterName)
//                        NotificationHelperImpl.showNotification(context, notyText)
                        createAlarm.invoke(4000L, name)
                        showNotification.invoke(notyText)
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