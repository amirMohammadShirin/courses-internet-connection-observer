package com.sweet.course.internetconnectionobserver.helper

import com.sweet.course.internetconnectionobserver.model.ConnectivityStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<ConnectivityStatus>.doOnLost(action: () -> Unit): Flow<ConnectivityStatus> {
    return this.map {
        if (it is ConnectivityStatus.Lost)
            action()
        it
    }
}

fun Flow<ConnectivityStatus>.doOnAvailable(action: () -> Unit): Flow<ConnectivityStatus> {
    return this.map {
        if (it is ConnectivityStatus.Available)
            action()
        it
    }
}

fun Flow<ConnectivityStatus>.doOnUnAvailable(action: () -> Unit): Flow<ConnectivityStatus> {
    return this.map {
        if (it is ConnectivityStatus.Unavailable)
            action()
        it
    }
}

fun Flow<ConnectivityStatus>.doOnCapabilityChanges(action: (it: ConnectivityStatus.CapabilityChanges) -> Unit): Flow<ConnectivityStatus> {
    return this.map {
        if (it is ConnectivityStatus.CapabilityChanges)
            action(it)
        it
    }
}

