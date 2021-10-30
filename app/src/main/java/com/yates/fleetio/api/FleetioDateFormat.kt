package com.yates.fleetio.api

import java.text.SimpleDateFormat
import java.util.*

class FleetioDateFormat {
    companion object {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        fun format(date: Date): String = dateFormat.format(date)
        fun parse(source: String) = dateFormat.parse(source) ?: Date()
    }
}