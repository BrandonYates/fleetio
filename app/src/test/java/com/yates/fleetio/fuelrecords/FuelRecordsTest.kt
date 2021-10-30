package com.yates.fleetio.fuelrecords

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.yates.fleetio.api.FleetioDateFormat

class FuelRecordsTest {

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
}