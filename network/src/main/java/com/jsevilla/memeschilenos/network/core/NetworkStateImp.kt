package com.jsevilla.memeschilenos.network.core

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import com.jsevilla.memeschilenos.network.net.NetworkEvents
import com.jsevilla.memeschilenos.network.net.Event
import com.jsevilla.memeschilenos.network.net.NetworkState

internal class NetworkStateImp : NetworkState {
    override var isConnected: Boolean = false
        set(value) {
            field = value
            NetworkEvents.notify(
                Event.ConnectivityEvent(
                    this
                )
            )
        }

    override var network: Network? = null

    override var linkProperties: LinkProperties? = null
        set(value) {
            val old = field
            field = value
            NetworkEvents.notify(
                Event.LinkPropertyChangeEvent(
                    this,
                    old
                )
            )
        }

    override var networkCapabilities: NetworkCapabilities? = null
        set(value) {
            val old = field
            field = value
            NetworkEvents.notify(
                Event.NetworkCapabilityEvent(
                    this,
                    old
                )
            )

        }
}
