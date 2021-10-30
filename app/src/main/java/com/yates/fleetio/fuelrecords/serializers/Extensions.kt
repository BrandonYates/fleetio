package com.yates.fleetio.fuelrecords.serializers

import com.google.gson.JsonElement
import com.google.gson.JsonObject

fun JsonObject.getOrNull(memberName: String): JsonElement? {
    val element = get(memberName)
    return if(element?.isJsonNull == true) {
        null
    } else {
        element
    }
}
