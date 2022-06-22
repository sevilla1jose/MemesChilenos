package com.jsevilla.memeschilenos.data.mock

import com.jsevilla.memeschilenos.data.network.utils.ConnectionUtils

class ConnectionUtilsImplMocked : ConnectionUtils {
    override fun isNetworkAvailable() = true
}
