package com.yates.fleetio.api

import okhttp3.*

const val BASE_API_URL = "https://secure.fleetio.com/api/v1/"

class FleetioClient {

    private val client = OkHttpClient()

    fun getFuelEntries() : Response {
        val request = Request.Builder()
            .url(BASE_API_URL + "fuel_entries")
            .addHeader("Authorization", "Token 6503cd0340f7976814e217dbc9cb8786b25087ec")
            .addHeader("Account-Token", "798819214b")
            .build()

        return client.newCall(request)
            .execute()
    }
}