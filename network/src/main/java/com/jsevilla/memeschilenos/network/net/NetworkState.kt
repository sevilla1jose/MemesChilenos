package com.jsevilla.memeschilenos.network.net

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities

interface NetworkState {
    /**
     * Stored connectivity state of the network
     * True if the device as access the the network
     */
    val isConnected: Boolean

    /**
     * The network being used by the device
     */
    val network: Network?

    /**
     * Network Capabilities
     */
    val networkCapabilities: NetworkCapabilities?

    /**
     * Link Properties
     */
    val linkProperties: LinkProperties?

    /**
     * Check if the network is Wifi ( shortcut )
     */
    val isWifi: Boolean
        get() = networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false

    /**
     * Check if the network is Mobile ( shortcut )
     */
    val isMobile: Boolean
        get() = networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false

    /**
     * Get the interface name ( shortcut )
     */
    val interfaceName: String?
        get() = linkProperties?.interfaceName
}
