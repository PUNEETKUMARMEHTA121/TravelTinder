package com.example.traveldiary.android.landingScreen.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.traveldiary.android.common.base.BaseFragment
import com.example.traveldiary.android.databinding.FragmentMyTripsBinding
import com.example.traveldiary.android.landingScreen.adapters.MyTripsAdapter
import com.example.traveldiary.android.landingScreen.model.Trip

class MyTripsFragment : BaseFragment<FragmentMyTripsBinding>() {

    private val trips = listOf(
        Trip.empty().copy(
            id = 1,
            destination = "Paris",
            startDate = "2024-07-01",
            endDate = "2024-07-10",
            teamSize = 4,
            description = "this is trip 1 description.",
            organizer = "Organizer1",
            imageUrl = mutableListOf<String>().apply {

            },
            createdDate = "2024-07-01",
        ),
        Trip.empty().copy(
            id = 2,
            destination = "New York",
            startDate = "2024-08-01",
            endDate = "2024-08-10",
            teamSize = 3,
            description = "this is trip 2 description.",
            organizer = "Organizer2",
            imageUrl = mutableListOf<String>().apply {

            },
            createdDate = "2024-08-01",
        )

    )

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyTripsBinding {
        return FragmentMyTripsBinding.inflate(inflater, container, false)
    }

    override fun fetchData() {
        binding?.trips?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MyTripsAdapter(trips)
        }
    }

    override fun setUpViews() {
        // Set up your views here
    }

    override fun setUpListeners() {
        // Set up your listeners here
        binding?.fabAddTrip?.setOnClickListener {
            // Handle the FAB click event
        }
    }
}
