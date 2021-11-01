package com.yates.fleetio.api

import android.util.Log
import org.junit.Test

class FleetioApiTest {

    @Test
    fun blah() {

        FleetioClient().getFuelEntries().also {
            Log.d("blah", it.message)
            Log.d("blah", it.body?.string() ?: "null")

            it.close()
        }
    }
}