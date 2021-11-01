package com.yates.fleetio.fuelrecords.serializers

import android.util.Log
import com.google.gson.*
import com.yates.fleetio.api.FleetioDateFormat
import com.yates.fleetio.fuelrecords.FuelRecord
import com.yates.fleetio.fuelrecords.MeterEntry
import java.lang.reflect.Type

class FuelRecordSerializer : JsonSerializer<FuelRecord>, JsonDeserializer<FuelRecord> {
    private val gson = GsonBuilder()
        .registerTypeAdapter(MeterEntry::class.java, MeterSerializer())
        .create()

    override fun serialize(
        src: FuelRecord?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement = src?.let { entry ->

        val obj = JsonObject()
        obj.addProperty("id", entry.id)
        entry.cost_per_hr?.also {
            obj.addProperty("cost_per_hr", gson.toJson(it))
        }
        entry.cost_per_km?.also {
            obj.addProperty("cost_per_km", gson.toJson(it))
        }
        entry.cost_per_mi?.also {
            obj.addProperty("cost_per_mi", gson.toJson(it))
        }
        obj.add("custom_fields", entry.custom_fields)
        obj.addProperty("date", FleetioDateFormat.format(entry.date))
        obj.addProperty("external_id", entry.external_id)
        obj.addProperty("fuel_type_id", entry.fuel_type_id)
        obj.addProperty("fuel_type_name", entry.fuel_type_name)
        entry.kpl?.also {
            obj.addProperty("kpl", gson.toJson(it))
        }
        entry.liters?.also {
            obj.addProperty("liters", gson.toJson(it))
        }
        entry.liters_per_hr?.also {
            obj.addProperty("liters_per_hr", gson.toJson(it))
        }
        entry.lp100k?.also {
            obj.addProperty("lp100k", gson.toJson(it))
        }
        entry.mpg_uk?.also {
            obj.addProperty("mpg_uk", gson.toJson(it))
        }
        entry.mpg_us?.also {
            obj.addProperty("mpg_us", gson.toJson(it))
        }
        obj.addProperty("partial", entry.partial)
        obj.addProperty("personal", entry.personal)
        entry.price_per_volume_unit?.also {
            obj.addProperty("price_per_volume_unit", gson.toJson(it))
        }
        obj.add("raw_transaction_data", entry.raw_transaction_data)
        obj.addProperty("reference", entry.reference)
        obj.addProperty("region", entry.region)
        obj.addProperty("reset", entry.reset)
        entry.uk_gallons.also {
            obj.addProperty("uk_gallons", gson.toJson(it))
        }
        entry.uk_gallons_per_hr?.also {
            obj.addProperty("uk_gallons_per_hr", gson.toJson(it))
        }
        entry.us_gallons.also {
            obj.addProperty("us_gallons", gson.toJson(it))
        }
        entry.us_gallons_per_hr?.also {
            obj.addProperty("us_gallons_per_hr", gson.toJson(it))
        }
        entry.usage_in_hr?.also {
            obj.addProperty("usage_in_hr", gson.toJson(it))
        }
        entry.usage_in_km?.also {
            obj.addProperty("usage_in_km", gson.toJson(it))
        }
        entry.usage_in_mi.also {
            obj.addProperty("usage_in_mi", gson.toJson(it))
        }
        obj.addProperty("vehicle_id", entry.vehicle_id)
        obj.addProperty("vehicle_name", entry.vehicle_name)
        obj.addProperty("vendor_id", entry.vendor_id)
        obj.addProperty("vendor_name", entry.vendor_name)
        entry.total_amount.also {
            obj.addProperty("total_amount", gson.toJson(it))
        }
        obj.add("meter_entry", gson.toJsonTree(entry.meter_entry))
        entry.created_at.also {
            obj.addProperty("created_at", FleetioDateFormat.format(it))
        }
        entry.updated_at?.also {
            obj.addProperty("updated_at", FleetioDateFormat.format(it))
        }
        obj
    } ?: JsonNull.INSTANCE

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): FuelRecord? = json?.let { jsonElement ->
        if (!jsonElement.isJsonNull && jsonElement.isJsonObject) {
            val obj = jsonElement as JsonObject
            try {
                getFuelRecord(obj)
            } catch (e: UnsupportedOperationException) {
                Log.e("FuelRecordSerializer", e.message, e)
                null
            }
        } else {
            null
        }
    }

    private fun getFuelRecord(obj: JsonObject): FuelRecord =
        FuelRecord(
            id = obj.get("id").asInt,
            cost_per_hr = obj.getOrNull("cost_per_hr")?.asFloat,
            cost_per_km = obj.getOrNull("cost_per_km")?.asFloat,
            cost_per_mi = obj.getOrNull("cost_per_mi")?.asFloat,
            custom_fields = obj.getAsJsonObject("custom_fields"),
            date = obj.get("date").asString.let(FleetioDateFormat::parse),
            external_id = obj.getOrNull("external_id")?.asInt,
            fuel_type_id = obj.getOrNull("fuel_type_id")?.asInt,
            fuel_type_name = obj.getOrNull("fuel_type_name")?.asString,
            kpl = obj.getOrNull("kpl")?.asFloat,
            liters = obj.getOrNull("liters")?.asFloat,
            liters_per_hr = obj.getOrNull("liters_per_hr")?.asFloat,
            lp100k = obj.getOrNull("lp100k")?.asFloat,
            mpg_uk = obj.getOrNull("mpg_uk")?.asFloat,
            mpg_us = obj.getOrNull("mpg_us")?.asFloat,
            partial = obj.getOrNull("partial")?.asBoolean,
            personal = obj.getOrNull("personal")?.asBoolean,
            price_per_volume_unit = obj.getOrNull("price_per_volume_unit")?.asFloat,
            raw_transaction_data = obj.getOrNull("raw_transaction_data")?.asJsonObject,
            reference = obj.getOrNull("reference")?.asString,
            region = obj.getOrNull("region")?.asString,
            reset = obj.getOrNull("reset")?.asBoolean,
            uk_gallons = obj.get("uk_gallons").asFloat,
            uk_gallons_per_hr = obj.getOrNull("us_gallons_per_hr")?.asFloat,
            us_gallons = obj.get("us_gallons").asFloat,
            us_gallons_per_hr = obj.getOrNull("us_gallons_per_hr")?.asFloat,
            usage_in_hr = obj.getOrNull("usage_in_hr")?.asFloat,
            usage_in_km = obj.getOrNull("usage_in_km")?.asFloat,
            usage_in_mi = obj.getOrNull("usage_in_mi")?.asFloat,
            vehicle_id = obj.get("vehicle_id").asInt,
            vehicle_name = obj.get("vehicle_name").asString,
            vendor_id = obj.getOrNull("vendor_id")?.asInt,
            vendor_name = obj.getOrNull("vendor_name")?.asString,
            total_amount = obj.get("total_amount").asFloat,
            meter_entry = gson.fromJson(obj.get("meter_entry"), MeterEntry::class.java),
            created_at = obj.get("created_at").asString.let(FleetioDateFormat::parse),
            updated_at = obj.getOrNull("updated_at")?.asString?.let(FleetioDateFormat::parse)
        )
}