package com.example.traveldiary.android.landingScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traveldiary.android.databinding.ItemTripBinding
import com.example.traveldiary.android.landingScreen.model.Trip

class MyTripsAdapter(
    private val tripList: List<Trip>
) : RecyclerView.Adapter<MyTripsAdapter.TripViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val binding = ItemTripBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TripViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val trip = tripList[position]
        holder.bind(trip)
    }

    override fun getItemCount(): Int {
        return tripList.size
    }

    class TripViewHolder(private val binding: ItemTripBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(trip: Trip) {
            binding.tvDestination.text = "Destination: ${trip.destination}"
            binding.tvDates.text = "Duration: ${trip.startDate} - ${trip.endDate}"
            binding.tvTeamSize.text = "Team Size: ${trip.teamSize}"
        }
    }
}