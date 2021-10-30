package com.yates.fleetio.fuelrecords.serializers

import com.google.gson.*
import com.yates.fleetio.api.FleetioDateFormat
import com.yates.fleetio.fuelrecords.MeterEntry
import java.lang.reflect.Type

class MeterSerializer : JsonSerializer<MeterEntry>, JsonDeserializer<MeterEntry> {

    override fun serialize(
        src: MeterEntry?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement = src?.let { entry ->
            val obj = JsonObject()
            obj.addProperty("id", entry.id)
            entry.auto_voided_at?.also {
                obj.addProperty("auto_voided_at", FleetioDateFormat.format(it))
            }
            obj.addProperty("category", entry.category)
            obj.addProperty("date", entry.date)
            obj.addProperty("meter_type", entry.meter_type)
            obj.addProperty("meterable_id", entry.meterable_id)
            obj.addProperty("meterable_type", entry.meterable_type)
            obj.addProperty("value", entry.value)
            obj.addProperty("vehicle_id", entry.vehicle_id)
            obj.addProperty("void", entry.void)
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
    ): MeterEntry? = json?.let { jsonElement ->
        if (!jsonElement.isJsonNull && jsonElement.isJsonObject) {
            val obj = jsonElement as JsonObject
            MeterEntry(
                id = obj.get("id").asInt,
                auto_voided_at = obj.getOrNull("auto_voided_at")?.asString?.let(FleetioDateFormat::parse),
                category = obj.getOrNull("category")?.asString,
                date = obj.get("date").asString,
                meter_type = obj.getOrNull("meter_type")?.asString,
                meterable_id = obj.get("meterable_id").asInt,
                meterable_type = obj.get("meterable_type").asString,
                value = obj.get("value").asInt,
                vehicle_id = obj.get("vehicle_id").asInt,
                void = obj.get("void").asBoolean,
                created_at = obj.get("created_at").asString.let(FleetioDateFormat::parse),
                updated_at = obj.getOrNull("updated_at")?.asString?.let(FleetioDateFormat::parse)
            )
        } else {
            null
        }
    }
}