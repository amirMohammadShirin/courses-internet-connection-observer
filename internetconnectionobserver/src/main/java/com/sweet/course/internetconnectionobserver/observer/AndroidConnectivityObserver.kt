package com.sweet.course.internetconnectionobserver.observer

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import androidx.core.content.getSystemService
import com.sweet.course.internetconnectionobserver.model.ConnectivityStatus
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AndroidConnectivityObserver(private val context: Context) : ConnectivityObserver {

    override fun connectionStatus(): Flow<ConnectivityStatus> = callbackFlow {

        getConnectivityManager(context)?.let { connectivityManager ->

            val callback = connectivityMangerCallback()
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                println("SWEET cleared")
                connectivityManager.unregisterNetworkCallback(callback) }

        }

        throw IllegalStateException("Connectivity Manager Not Found")

    }

    private fun getConnectivityManager(context: Context): ConnectivityManager? =
        context.getSystemService<ConnectivityManager>()

    private fun ProducerScope<ConnectivityStatus>.connectivityMangerCallback(): NetworkCallback {

        val callback = object : NetworkCallback() {

            override fun onUnavailable() {
                super.onUnavailable()
                trySend(ConnectivityStatus.Unavailable)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(ConnectivityStatus.Lost)
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(ConnectivityStatus.Available)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                trySend(
                    ConnectivityStatus.CapabilityChanges(
                        isConnected = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    )
                )
            }

        }

        return callback

    }

}