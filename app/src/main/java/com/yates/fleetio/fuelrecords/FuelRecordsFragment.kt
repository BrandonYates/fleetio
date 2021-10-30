package com.yates.fleetio.fuelrecords

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.yates.fleetio.api.FleetioClient
import com.yates.fleetio.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class FuelRecordsFragment : Fragment() {

    companion object {
        fun newInstance() = FuelRecordsFragment()
    }

    private lateinit var viewModel: FuelRecordsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FuelRecordsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fuel_records_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FleetioClient().getFuelEntries(GetFuelEntriesCallback())
    }

    private inner class GetFuelEntriesCallback: Callback {
        override fun onResponse(call: Call, response: Response) {
            if(call.isExecuted() && response.isSuccessful) {
                response.body?.also { body ->
                    Log.d("FleetioApi", "contentType: ${body.contentType()}")
                    Log.d("FleetioApi", "contentLength: ${body.contentLength()}")
                    Log.d("FleetioApi", "contentType: ${body.string()}")

                    Gson().fromJson(body.string(), JsonObject::class.java).also { json ->
                        viewModel.updateRecords(json)
                    }
                }
            }
        }

        override fun onFailure(call: Call, e: IOException) {
            Log.e("FleetioApi", e.message, e)
        }
    }

}