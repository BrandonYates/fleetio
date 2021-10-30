package com.yates.fleetio.fuelrecords

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonArray
import com.google.gson.JsonElement

class FuelRecordsViewModel : ViewModel() {

    val _records =  MutableLiveData<JsonArray>()

    val records: LiveData<JsonArray>
        get() = _records

    fun updateRecords(jsonElement: JsonElement) {
        if(jsonElement.isJsonArray) {
            _records.postValue(jsonElement.asJsonArray)
        }
    }
}