package com.sweet.course.internetconnectionobserver.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sweet.course.internetconnectionobserver.MainUiModel
import com.sweet.course.internetconnectionobserver.MainViewModel
import com.sweet.course.internetconnectionobserver.demo.ui.theme.InternetConnectionObserverTheme
import com.sweet.course.internetconnectionobserver.observer.AndroidConnectivityObserver

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createViewModel()
        enableEdgeToEdge()
        setContent {
            val uiState = viewModel.uiState.collectAsState(MainUiModel.initialValue)
            MainScreen(
                uiState.value.isInternetConnected,
                uiState.value.statusColor,
                uiState.value.status
            )
        }
    }

    private fun createViewModel() {
        viewModel = MainViewModel(
            connectivityObserver = AndroidConnectivityObserver(applicationContext)
        )
    }

}

@Composable
fun MainScreen(
    isConnected: Boolean,
    statusColor: Color,
    status: String
) {
    InternetConnectionObserverTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .background(statusColor)
                        .padding(20.dp)
                        .fillMaxWidth(0.5f)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Connected $isConnected",
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = status, textAlign = TextAlign.Center, fontSize = 12.sp
                    )
                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    InternetConnectionObserverTheme {
        MainScreen(
            true,
            Color.Green,
            "Test status"
        )
    }
}