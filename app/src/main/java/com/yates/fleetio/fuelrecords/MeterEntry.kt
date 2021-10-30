package com.yates.fleetio.fuelrecords

import com.yates.fleetio.api.FleetioDateFormat
import java.util.Date

data class MeterEntry(
    val id: Int,
    val auto_voided_at: Date?,
    val category: String?,
    val date: String,
    val meter_type: String?,
    val meterable_id: Int,
    val meterable_type: String,
    val value: Int,
    val vehicle_id: Int,
    val void: Boolean,
    val created_at: Date,
    val updated_at: Date?
) {

    constructor(
        id: Int,
        auto_voided_at: String?,
        category: String?,
        date: String,
        meter_type: String?,
        meterable_id: Int,
        meterable_type: String,
        value: Int,
        vehicle_id: Int,
        void: Boolean,
        created_at: String,
        updated_at: String?
    ) : this(
        id = id,
        auto_voided_at = auto_voided_at?.let(FleetioDateFormat::parse),
        category = category,
        date = date,
        meter_type = meter_type,
        meterable_id = meterable_id,
        meterable_type = meterable_type,
        value = value,
        vehicle_id = vehicle_id,
        void = void,
        created_at = created_at.let(FleetioDateFormat::parse),
        updated_at = updated_at?.let(FleetioDateFormat::parse)
    )
}
