package com.example.traveldiary.android.landingScreen.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.traveldiary.android.common.base.BaseFragment
import com.example.traveldiary.android.databinding.FragmentDiscoverBinding
import com.example.traveldiary.android.landingScreen.adapters.ProfileCardAdapter
import com.example.traveldiary.android.landingScreen.model.UserProfile
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom

class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>() {

    private lateinit var mCardStackView: CardStackView
    private lateinit var manager: CardStackLayoutManager
    private lateinit var adapter: ProfileCardAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDiscoverBinding {
        return FragmentDiscoverBinding.inflate(inflater, container, false)
    }

    override fun fetchData() {
        // Fetch data if necessary
    }

    override fun setUpViews() {
        // Set up your views here
        binding?.apply {
            mCardStackView = cardStackView
            manager = CardStackLayoutManager(requireContext(), object : CardStackListener {
                override fun onCardDragging(direction: Direction?, ratio: Float) {
                    val viewHolder = cardStackView.findViewHolderForAdapterPosition(manager.topPosition) as ProfileCardAdapter.ProfileViewHolder
                    when (direction) {
                        Direction.Left -> viewHolder.binding.crossImage.visibility = View.VISIBLE
                        Direction.Right -> viewHolder.binding.tickImage.visibility = View.VISIBLE
                        else -> {
                            viewHolder.binding.crossImage.visibility = View.GONE
                            viewHolder.binding.tickImage.visibility = View.GONE
                        }
                    }
                }

                override fun onCardSwiped(direction: Direction?) {
                    if (direction == Direction.Right) {
                        // Handle right swipe (like)
                        Toast.makeText(requireContext(), "Liked!", Toast.LENGTH_SHORT).show()
                    } else if (direction == Direction.Left) {
                        // Handle left swipe (dislike)
                        Toast.makeText(requireContext(), "Disliked!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCardRewound() {}

                override fun onCardCanceled() {}

                override fun onCardAppeared(view: View?, position: Int) {}

                override fun onCardDisappeared(view: View?, position: Int) {}
            })

            manager.setStackFrom(StackFrom.None)
            manager.setVisibleCount(1)
            manager.setTranslationInterval(8.0f)
            manager.setScaleInterval(0.95f)
            manager.setSwipeThreshold(0.3f)
            manager.setMaxDegree(20.0f)
            manager.setDirections(Direction.HORIZONTAL)
            manager.setCanScrollHorizontal(true)
            manager.setCanScrollVertical(false)

            adapter = ProfileCardAdapter(getProfiles())
            cardStackView.layoutManager = manager
            cardStackView.adapter = adapter
        }
    }

    override fun setUpListeners() {
        // Set up your listeners here
    }

    private fun getProfiles(): List<UserProfile> {
        // Replace with actual data retrieval
        return listOf(
            UserProfile("1", "John Doe", "https://firebasestorage.googleapis.com/v0/b/zobaze-pos.appspot.com/o/imgInApp%2Fon_boarding%2Foct2020%2FSales%20counter.png?alt=media&token=7e1e108b-dcb0-4d49-9265-6a250cf61c7e"),
            UserProfile("2", "Jane Smith", "https://firebasestorage.googleapis.com/v0/b/zobaze-pos.appspot.com/o/imgInApp%2Fon_boarding%2Foct2020%2FInventory%20management.png?alt=media&token=2c7ba611-8e50-4d08-85e9-ec01d13fb476"),
            // Add more profiles
        )
    }
}
