package com.sweet.course.internetconnectionobserver.observer

import com.sweet.course.internetconnectionobserver.model.ConnectivityStatus
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws


interface ConnectivityObserver {

    @Throws(IllegalStateException::class)
    fun connectionStatus(): Flow<ConnectivityStatus>

}