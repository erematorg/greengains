package com.landomen.sample.foregroundservice.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.landomen.sample.foregroundservice.R
import com.landomen.sample.foregroundservice.ui.theme.ForegroundServiceTheme

@Composable
internal fun WorkManagerScreen(
    workerRunning: Boolean,
    currentLocation: String?,
    onStartWorkManagerClick: () -> Unit,
    onStopWorkManagerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ForegroundServiceTheme {
        Surface(
            modifier = Modifier.fillMaxSize().systemBarsPadding(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.work_manager_sample_description),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        WorkerStatusContent(
                            workerRunning = workerRunning,
                            onStartClick = onStartWorkManagerClick,
                            onStopClick = onStopWorkManagerClick
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        LocationUpdate(visible = workerRunning, location = currentLocation)
                    }
                }
            }
        }
    }
}

@Composable
private fun WorkerStatusContent(
    workerRunning: Boolean,
    onStartClick: () -> Unit,
    onStopClick: () -> Unit
) {
    WorkerStatusRow(workerRunning = workerRunning)

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = if (workerRunning) onStopClick else onStartClick) {
        Text(
            text = stringResource(
                id = if (workerRunning) {
                    R.string.work_manager_sample_button_stop
                } else {
                    R.string.work_manager_sample_button_start
                }
            )
        )
    }
}

@Composable
private fun WorkerStatusRow(
    workerRunning: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.work_manager_sample_status_title),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = if (workerRunning) {
                stringResource(id = R.string.foreground_service_sample_status_running)
            } else {
                stringResource(id = R.string.foreground_service_sample_status_not_running)
            },
            color = if (workerRunning) {
                Color.Green
            } else {
                Color.Red
            },
        )
    }
}

@Composable
private fun LocationUpdate(
    visible: Boolean,
    location: String?,
) {
    if (visible) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(id = R.string.foreground_service_sample_last_location_title),
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = location
                ?: stringResource(id = R.string.foreground_service_sample_last_location_fetching)
        )
    }
}

@Preview
@Composable
private fun WorkManagerScreenPreview() {
    WorkManagerScreen(
        workerRunning = false,
        currentLocation = "37.7901088, -122.3905696",
        onStartWorkManagerClick = {},
        onStopWorkManagerClick = {}
    )
}
