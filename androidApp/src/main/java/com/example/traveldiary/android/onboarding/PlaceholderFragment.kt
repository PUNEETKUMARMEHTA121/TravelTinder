package com.example.traveldiary.android.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.traveldiary.android.R
import com.example.traveldiary.android.databinding.PlaceHolderFragmentBinding

class PlaceholderFragment : Fragment() {
    private var _binding: PlaceHolderFragmentBinding? = null
    private val binding get() = _binding!!

    private val titleResIds = listOf(
        R.string.landing_base_v2_onboard_title_1,
        R.string.landing_base_v2_onboard_title_2,
        R.string.landing_base_v2_onboard_title_3,
        R.string.landing_base_v2_onboard_title_4
    )

    private val subTitleResIds = listOf(
        R.string.landing_base_v2_onboard_sub_title_1,
        R.string.landing_base_v2_onboard_sub_title_2,
        R.string.landing_base_v2_onboard_sub_title_3,
        R.string.landing_base_v2_onboard_sub_title_4
    )

//    private val viewModel: PlaceHolderViewModel by viewModel()
//    private val eventViewModel: EventsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PlaceHolderFragmentBinding.inflate(inflater, container, false)
        val index = arguments?.getInt(ARG_SECTION_NUMBER) ?: -1

//        viewModel.getImageUrl(index).observe(viewLifecycleOwner) { imageUrl ->
//            urlToImage(binding.image, imageUrl)
//        }

        binding.title.text = getString(titleResIds[index])
        binding.sub.text = getString(subTitleResIds[index])

        pushEvents()

        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(index: Int): PlaceholderFragment {
            val fragment = PlaceholderFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun pushEvents() {
//        eventViewModel.addEvent(USER_LOGIN, Bundle().apply {
//            this.putString("customer_id", "P2bbGXuqH6ekJuL181UHNlqDY2E2")
//            this.putString("auth_mode", "" + "google")
//            this.putString("full_name", "Prakash")
//        })
    }
}
