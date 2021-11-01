package com.yates.fleetio.fuelrecords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yates.fleetio.R
import com.yates.fleetio.api.FleetioDateFormat
import com.yates.fleetio.databinding.FuelRecordRowItemBinding
import java.text.SimpleDateFormat

class FuelRecordAdapter : ListAdapter<FuelRecord, FuelRecordAdapter.FuelRecordViewHolder>(FuelRecordDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuelRecordAdapter.FuelRecordViewHolder {
        val binding = FuelRecordRowItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return FuelRecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FuelRecordAdapter.FuelRecordViewHolder, position: Int) {
        currentList[position].also { record ->
            with(holder) {
                binding.tvDate.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(record.date)
                binding.tvVehicleName.text = record.vehicle_name
                binding.tvCost.text = record.cost_per_hr.getText(binding)
                binding.tvCpm.text = record.cost_per_mi.getText(binding)
                binding.tvFuelType.text = record.fuel_type_name.getText(binding)
                binding.tvGallons.text = record.us_gallons.toString()
                binding.tvPpg.text = record.price_per_volume_unit.getText(binding)
                binding.tvRefNum.text = record.id.toString()
            }
        }
    }

    private fun Float?.getText(binding: FuelRecordRowItemBinding) =
        this?.toString() ?: binding.root.resources.getText(R.string.notAvailable)

    private fun String?.getText(binding: FuelRecordRowItemBinding) =
        this?.toString() ?: binding.root.resources.getText(R.string.notAvailable)

    override fun getItemCount(): Int = currentList.size

    inner class FuelRecordViewHolder(val binding: FuelRecordRowItemBinding): RecyclerView.ViewHolder(binding.root)
}

object FuelRecordDiffCallback : DiffUtil.ItemCallback<FuelRecord>() {
    override fun areItemsTheSame(oldItem: FuelRecord, newItem: FuelRecord): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FuelRecord, newItem: FuelRecord): Boolean {
        return oldItem.id == newItem.id
    }
}