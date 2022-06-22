package com.jsevilla.memeschilenos.network.net

import android.app.Application
import android.content.Context
import android.net.*
import com.jsevilla.memeschilenos.network.core.ActivityLifecycleCallbacksImp
import com.jsevilla.memeschilenos.network.core.NetworkCallbackImp
import com.jsevilla.memeschilenos.network.core.NetworkStateImp

object NetworkStateHolder : NetworkState {
    private lateinit var holder: NetworkStateImp

    override val isConnected: Boolean
        get() = holder.isConnected

    override val network: Network?
        get() = holder.network

    override val networkCapabilities: NetworkCapabilities?
        get() = holder.networkCapabilities

    override val linkProperties: LinkProperties?
        get() = holder.linkProperties

    /**
     * This starts the broadcast of network events to NetworkState and all Activity implementing NetworkConnectivityListener
     * @see NetworkState
     * @see NetworkConnectivityListener
     */
    fun Application.NetworkConnectivityBroadcaster() {
        holder = NetworkStateImp()

        //register tje Activity Broadcaster
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksImp(holder))

        //get connectivity manager
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //register to network events
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), NetworkCallbackImp(holder))
    }
}
