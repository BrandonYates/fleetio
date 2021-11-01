package com.yates.fleetio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.yates.fleetio.databinding.ActivityMainBinding
import com.yates.fleetio.ui.main.MainActivityPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val titles = arrayOf(R.string.tab_text_fuel_records, R.string.tab_text_map)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = MainActivityPagerAdapter(this)
        val viewPager: ViewPager2 = binding.pager

        viewPager.adapter = sectionsPagerAdapter
        viewPager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
            tab.text = getString(titles[position])
        }.attach()

    }
}