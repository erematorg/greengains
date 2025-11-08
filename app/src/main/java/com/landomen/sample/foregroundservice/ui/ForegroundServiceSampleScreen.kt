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
import androidx.compose.material3.HorizontalDivider
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

sealed interface ServiceState {
    val running: Boolean

    data class ForegroundService(
        override val running: Boolean,
    ) : ServiceState

    data class WorkManager(
        override val running: Boolean,
    ) : ServiceState
}

@Composable
internal fun ForegroundServiceSampleScreen(
    serviceState: ServiceState?,
    currentLocation: String?,
    onStartForegroundServiceClick: () -> Unit,
    onStartWorkManagerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ForegroundServiceTheme {
        Surface(
            modifier = Modifier.fillMaxSize().systemBarsPadding(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                ForegroundServiceSampleScreenContent(
                    serviceRunning = serviceState?.running == true,
                    currentLocation = currentLocation,
                    onClick = onStartForegroundServiceClick,
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(4f, false)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f, false)
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(32.dp))

                    Button(onClick = onStartWorkManagerClick) {
                        Text(
                            text = stringResource(
                                id = R.string.work_manager_sample_button_start
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ForegroundServiceSampleScreenContent(
    serviceRunning: Boolean,
    currentLocation: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.foreground_service_sample_description),
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
                ServiceStatusContent(
                    serviceRunning = serviceRunning,
                    onClick = onClick
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                LocationUpdate(visible = serviceRunning, location = currentLocation)
            }
        }
    }
}

@Composable
private fun ServiceStatusContent(
    serviceRunning: Boolean,
    onClick: () -> Unit
) {
    ServiceStatusRow(serviceRunning = serviceRunning)

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = onClick) {
        Text(
            text = stringResource(
                id = if (serviceRunning) {
                    R.string.foreground_service_sample_button_stop
                } else {
                    R.string.foreground_service_sample_button_start
                }
            )
        )
    }
}

@Composable
private fun ServiceStatusRow(
    serviceRunning: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.foreground_service_sample_status_title),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = if (serviceRunning) {
                stringResource(id = R.string.foreground_service_sample_status_running)
            } else {
                stringResource(id = R.string.foreground_service_sample_status_not_running)
            },
            color = if (serviceRunning) {
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
private fun ForegroundServiceSampleScreenPreview() {
    ForegroundServiceSampleScreen(
        serviceState = ServiceState.ForegroundService(running = false),
        currentLocation = "37.7901088, -122.3905696",
        onStartForegroundServiceClick = {},
        onStartWorkManagerClick = {}
    )

}