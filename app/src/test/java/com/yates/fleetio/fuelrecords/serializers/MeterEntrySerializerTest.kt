package com.yates.fleetio.fuelrecords.serializers

import com.google.gson.GsonBuilder
import com.yates.fleetio.fuelrecords.MeterEntry
import org.junit.Assert
import org.junit.Test

class MeterEntrySerializerTest {

    private val testMeterEntry = MeterEntry(
        id = 1,
        auto_voided_at = null,
        category = null,
        date = "2014-01-07",
        meter_type = null,
        meterable_id = 1,
        meterable_type = "FuelEntry",
        value = 284899,
        vehicle_id = 1,
        void = false,
        created_at = "2014-02-09T20:36:43.899-06:00",
        updated_at = "2014-12-23T11:32:51.672-06:00"
    )

    private val testMeterEntryJson = "{\n" +
            "      \"id\": 1,\n" +
            "      \"auto_voided_at\": null,\n" +
            "      \"category\": null,\n" +
            "      \"date\": \"2014-01-07\",\n" +
            "      \"meter_type\": null,\n" +
            "      \"meterable_id\": 1,\n" +
            "      \"meterable_type\": \"FuelEntry\",\n" +
            "      \"value\": 284899,\n" +
            "      \"vehicle_id\": 1,\n" +
            "      \"void\": false,\n" +
            "      \"created_at\": \"2014-02-09T20:36:43.899-06:00\",\n" +
            "      \"updated_at\": \"2014-12-23T11:32:51.672-06:00\"\n" +
            "    }"

    @Test
    fun testSerializerSimple() {
        val gson = GsonBuilder()
            .registerTypeAdapter(MeterEntry::class.java, MeterSerializer())
            .create()

        val serialized = gson.toJson(testMeterEntry)
        val deserialized = gson.fromJson(serialized, MeterEntry::class.java)

        Assert.assertEquals(testMeterEntry, deserialized)
    }

    @Test
    fun testDeserializerSimple() {

        val gson = GsonBuilder()
            .registerTypeAdapter(MeterEntry::class.java, MeterSerializer())
            .create()

        val deserialized = gson.fromJson(testMeterEntryJson, MeterEntry::class.java)

        Assert.assertEquals(testMeterEntry, deserialized)
    }

    @Test
    fun testTwoWaySerialization() {
        val json = testMeterEntryJson

        val gson = GsonBuilder()
            .registerTypeAdapter(MeterEntry::class.java, MeterSerializer())
            .create()

        val deserialized = gson.fromJson(json, MeterEntry::class.java)
        val serialized = gson.toJson(deserialized)
        val deserialized2 = gson.fromJson(serialized, MeterEntry::class.java)

        Assert.assertEquals(deserialized, deserialized2)
    }
}