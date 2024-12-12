package com.example.traveldiary.android.landingScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.traveldiary.android.databinding.ItemDiscoverCardBinding
import com.example.traveldiary.android.landingScreen.model.UserProfile

class ProfileCardAdapter(private val profiles: List<UserProfile>) :
    RecyclerView.Adapter<ProfileCardAdapter.ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemDiscoverCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = profiles[position]
        holder.bind(profile)
    }

    override fun getItemCount(): Int {
        return profiles.size
    }

    class ProfileViewHolder(val binding: ItemDiscoverCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: UserProfile) {
            binding.profileName.text = profile.name
            // Load image using Glide
            Glide.with(binding.profileImage.context)
                .load(profile.imageUrl)
                .into(binding.profileImage)
        }
    }
}
