package com.example.traveldiary.android.add_trip.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.traveldiary.android.common.base.BaseFragment
import com.example.traveldiary.android.databinding.FragmentAddTripBinding

class AddTripFragment : BaseFragment<FragmentAddTripBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddTripBinding {
        return FragmentAddTripBinding.inflate(inflater, container, false)
    }

    override fun fetchData() {
    }

    override fun setUpViews() {
    }

    override fun setUpListeners() {
    }
}