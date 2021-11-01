package com.yates.fleetio.fuelrecords

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.yates.fleetio.api.FleetioClient
import com.yates.fleetio.fuelrecords.serializers.FuelRecordSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.closeQuietly
import java.io.BufferedReader

class FuelRecordsViewModel : ViewModel() {

    val gson = GsonBuilder()
        .registerTypeAdapter(FuelRecord::class.java, FuelRecordSerializer())
        .create()

    init {
        viewModelScope.launch {
            fetchRecords()
        }
    }

    val _records =  MutableLiveData<List<FuelRecord>>()

    val records: LiveData<List<FuelRecord>>
        get() = _records

    private suspend fun fetchRecords() {
        withContext(Dispatchers.IO) {
            val response = FleetioClient().getFuelEntries()
            if(response.isSuccessful && response.body != null) {
                response.body?.also { body ->
                    val reader = BufferedReader(body.byteStream().reader())
                    val content = StringBuilder()
                    try {
                        reader.lines().forEach {
                            content.append(it)
                        }
                    } finally {
                        reader.closeQuietly()
                        response.closeQuietly()
                        gson.fromJson(content.toString(), JsonArray::class.java).map {
                            gson.fromJson(it, FuelRecord::class.java)
                        }.also {  records ->
                            _records.postValue(records)
                        }
                    }
                }
            } else {
                Log.e("fetchRecords", response.message)
                response.closeQuietly()
            }
        }
    }
}