package com.sweet.course.internetconnectionobserver.model

sealed class ConnectivityStatus {

    data object Lost : ConnectivityStatus()
    data object Available : ConnectivityStatus()
    data object Unavailable : ConnectivityStatus()
    data class CapabilityChanges(val isConnected: Boolean) : ConnectivityStatus()

}