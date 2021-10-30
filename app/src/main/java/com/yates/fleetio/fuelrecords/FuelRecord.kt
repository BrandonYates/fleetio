package com.yates.fleetio.fuelrecords

import com.google.gson.JsonObject
import com.yates.fleetio.api.FleetioDateFormat
import java.util.*

data class FuelRecord(
    val id: Int,
    val cost_per_hr: Float?,
    val cost_per_km: Float?,
    val cost_per_mi: Float?,
    val custom_fields: JsonObject = JsonObject(),
    val date: Date = Date(),
    val external_id: Int,
    val fuel_type_id: Int,
    val fuel_type_name: String,
    val kpl: Float?,
    val liters: Float?,
    val liters_per_hr: Float?,
    val lp100k: Float?,
    val mpg_uk: Float?,
    val mpg_us: Float?,
    val partial: Boolean,
    val personal: Boolean,
    val price_per_volume_unit: Float?,
    val raw_transaction_data: JsonObject = JsonObject(),
    val reference: String,
    val region: String?,
    val reset: Boolean,
    val uk_gallons: Float,
    val uk_gallons_per_hr: Float?,
    val us_gallons: Float,
    val us_gallons_per_hr: Float?,
    val usage_in_hr: Float?,
    val usage_in_km: Float?,
    val usage_in_mi: Float,
    val vehicle_id: Int,
    val vehicle_name: String,
    val vendor_id: Int,
    val vendor_name: String,
    val total_amount: Float,
    val meter_entry: MeterEntry,
    val created_at: Date,
    val updated_at: Date?
)
