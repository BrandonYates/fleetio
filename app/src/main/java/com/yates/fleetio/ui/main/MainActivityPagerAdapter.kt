package com.yates.fleetio.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yates.fleetio.fuelrecords.FuelRecordFragment
import com.yates.fleetio.map.MapFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class MainActivityPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        if(position == 0) {
            FuelRecordFragment.newInstance()
        } else  {
            MapFragment.newInstance()
        }
}