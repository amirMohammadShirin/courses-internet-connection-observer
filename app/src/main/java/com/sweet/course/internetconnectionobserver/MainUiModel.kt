package com.sweet.course.internetconnectionobserver

import androidx.compose.ui.graphics.Color

data class MainUiModel(
    val isInternetConnected: Boolean = false,
    val statusColor: Color = Color.Unspecified,
    val status: String
) {

    companion object {
        val initialValue = MainUiModel(
            isInternetConnected = false,
            statusColor = Color.Gray,
            status = "Waiting for Available Networks..."
        )
    }

}
