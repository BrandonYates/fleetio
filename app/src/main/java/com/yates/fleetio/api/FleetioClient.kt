package com.yates.fleetio.api

import android.util.JsonReader
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

const val BASE_API_URL = "https://secure.fleetio.com/api/v1/"

class FleetioClient {

    private val client = OkHttpClient()

    /* curl \
  --header "Authorization: Token 6503cd0340f7976814e217dbc9cb8786b25087ec" \
  --header "Account-Token: 798819214b" \
  "https://secure.fleetio.com/api/v1/fuel_entries" */

    fun getFuelEntries(callback: Callback) {
        val request = Request.Builder()
            .url(BASE_API_URL)
            .addHeader("Authorization", "Token 6503cd0340f7976814e217dbc9cb8786b25087ec")
            .addHeader("Acccount-Token", "798819214b")
            .build()

        client.newCall(request)
            .enqueue(callback)
    }
}