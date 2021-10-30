package com.yates.fleetio.fuelrecords.serializers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.yates.fleetio.api.FleetioDateFormat
import com.yates.fleetio.fuelrecords.FuelRecord
import com.yates.fleetio.fuelrecords.MeterEntry
import org.junit.Assert
import org.junit.Test

class FuelRecordSerializerTest {

    private val testMeterEntry = MeterEntry(
        id = 1,
        auto_voided_at = null as String?,
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

    val testFuelRecord = FuelRecord(
        id = 1,
        cost_per_hr = null,
        cost_per_km = 0.126f,
        cost_per_mi = 0.203f,
        custom_fields = Gson().fromJson(
            "{\n\t\"field_1\": \"value 1\"\n}",
            JsonObject::class.java),
        date = FleetioDateFormat.parse("2014-01-07T12:30:00.000-06:00"),
        external_id = 12345,
        fuel_type_id = 1,
        fuel_type_name = "Unleaded",
        kpl = 7.2f,
        liters = 111.064f,
        liters_per_hr = null,
        lp100k = 13.8f,
        mpg_uk = 20.5f,
        mpg_us = 17f,
        partial = false,
        personal = false,
        price_per_volume_unit = 3.46f,
        reference = "",
        region = null,
        reset = false,
        uk_gallons = 24.431f,
        uk_gallons_per_hr = null,
        us_gallons = 29.34f,
        us_gallons_per_hr = null,
        usage_in_hr = null,
        usage_in_km = 804.7f,
        usage_in_mi = 500f,
        vehicle_id = 1,
        vehicle_name = "#1 - WHITE FORD F-250",
        vendor_id = 1,
        vendor_name = "Friendly Neighborhood Gas Station",
        total_amount = 101.52f,
        meter_entry = testMeterEntry,
        created_at = FleetioDateFormat.parse("2014-02-09T20:36:43.885-06:00"),
        updated_at = FleetioDateFormat.parse("2015-01-21T13:04:33.055-06:00")
    )

    val testFuelRecordJson = "  {\n" +
            "    \"id\": 1,\n" +
            "    \"cost_per_hr\": null,\n" +
            "    \"cost_per_km\": 0.126,\n" +
            "    \"cost_per_mi\": 0.203,\n" +
            "    \"custom_fields\": {\n" +
            "      \"field_1\": \"value 1\"\n" +
            "    },\n" +
            "    \"date\": \"2014-01-07T12:30:00.000-06:00\",\n" +
            "    \"external_id\": \"12345\",\n" +
            "    \"fuel_type_id\": 1,\n" +
            "    \"fuel_type_name\": \"Unleaded\",\n" +
            "    \"kpl\": 7.2,\n" +
            "    \"liters\": 111.064,\n" +
            "    \"liters_per_hr\": null,\n" +
            "    \"lp100k\": 13.8,\n" +
            "    \"mpg_uk\": 20.5,\n" +
            "    \"mpg_us\": 17,\n" +
            "    \"partial\": false,\n" +
            "    \"personal\": false,\n" +
            "    \"price_per_volume_unit\": 3.46,\n" +
            "    \"raw_transaction_data\": {},\n" +
            "    \"reference\": \"\",\n" +
            "    \"region\": null,\n" +
            "    \"reset\": false,\n" +
            "    \"uk_gallons\": 24.431,\n" +
            "    \"uk_gallons_per_hr\": null,\n" +
            "    \"us_gallons\": 29.34,\n" +
            "    \"us_gallons_per_hr\": null,\n" +
            "    \"usage_in_hr\": null,\n" +
            "    \"usage_in_km\": 804.7,\n" +
            "    \"usage_in_mi\": 500,\n" +
            "    \"vehicle_id\": 1,\n" +
            "    \"vehicle_name\": \"#1 - WHITE FORD F-250\",\n" +
            "    \"vendor_id\": 1,\n" +
            "    \"vendor_name\": \"Friendly Neighborhood Gas Station\",\n" +
            "    \"total_amount\": 101.52,\n" +
            "    \"meter_entry\": {\n" +
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
            "    },\n" +
            "    \"created_at\": \"2014-02-09T20:36:43.885-06:00\",\n" +
            "    \"updated_at\": \"2015-01-21T13:04:33.055-06:00\"\n" +
            "  }"
//                .trimMargin("\\n").toRegex().replace("\\S", "")

    @Test
    fun testSerializer() {
        val gson = GsonBuilder()
            .registerTypeAdapter(FuelRecord::class.java, FuelRecordSerializer())
            .create()

        val serialized = gson.toJson(testFuelRecord)
        val deserialized = gson.fromJson(serialized, FuelRecord::class.java)

        Assert.assertEquals(testFuelRecord, deserialized)
    }

    @Test
    fun testDeserialize() {
        val gson = GsonBuilder()
            .registerTypeAdapter(FuelRecord::class.java, FuelRecordSerializer())
            .create()

        val deserialized = gson.fromJson(testFuelRecordJson, FuelRecord::class.java)

        Assert.assertEquals(testFuelRecord, deserialized)
    }

    @Test
    fun testTwoWaySerialization() {
        val gson = GsonBuilder()
            .registerTypeAdapter(FuelRecord::class.java, FuelRecordSerializer())
            .create()

        val deserialized = gson.fromJson(testFuelRecordJson, FuelRecord::class.java)
        val serialized = gson.toJson(deserialized)
        val deserialized2 = gson.fromJson(serialized, FuelRecord::class.java)

        Assert.assertEquals(deserialized, deserialized2)
    }
}