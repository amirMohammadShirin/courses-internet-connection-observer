package com.sweet.course.internetconnectionobserver

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweet.course.internetconnectionobserver.helper.doOnAvailable
import com.sweet.course.internetconnectionobserver.helper.doOnCapabilityChanges
import com.sweet.course.internetconnectionobserver.helper.doOnLost
import com.sweet.course.internetconnectionobserver.helper.doOnUnAvailable
import com.sweet.course.internetconnectionobserver.model.ConnectivityStatus
import com.sweet.course.internetconnectionobserver.observer.ConnectivityObserver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    connectivityObserver: ConnectivityObserver
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiModel.initialValue)
    val uiState: Flow<MainUiModel>
        get() = _uiState

    private val internetConnectionStatus = connectivityObserver
        .connectionStatus()
        .onEach {
            println("SWEET onEach $it")
        }
        .doOnLost {
            _uiState.update {
                it.copy(
                    isInternetConnected = false,
                    statusColor = Color.Red,
                    status = "Connection Lost..."
                )
            }
        }
        .doOnAvailable {
            _uiState.update {
                it.copy(
                    isInternetConnected = true,
                    statusColor = Color.Green,
                    status = "Connected"
                )
            }
        }
        .doOnUnAvailable {
            _uiState.update {
                it.copy(
                    isInternetConnected = false,
                    statusColor = Color.Yellow,
                    status = "Network not Available"
                )
            }
        }
        .doOnCapabilityChanges { connectionStatus ->
            _uiState.update {
                it.copy(
                    isInternetConnected = connectionStatus.isConnected,
                    statusColor = if (connectionStatus.isConnected) Color.Green else Color.Blue,
                    status = if (connectionStatus.isConnected) "Connected" else "Connecting",
                )
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(3000L),
            ConnectivityStatus.Unavailable
        )

    init {
        viewModelScope.launch {
            internetConnectionStatus.collect()
        }
    }

}