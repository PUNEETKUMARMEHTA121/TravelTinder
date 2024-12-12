package com.example.traveldiary.android.loginregisterauth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.traveldiary.android.common.base.BaseFragment
import com.example.traveldiary.android.databinding.FragmentUserTypeSelectionBinding

class UserTypeSelectionFragment : BaseFragment<FragmentUserTypeSelectionBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserTypeSelectionBinding {
        return FragmentUserTypeSelectionBinding.inflate(inflater, container, false)
    }

    override fun fetchData() {

    }

    override fun setUpViews() {

    }

    override fun setUpListeners() {
        binding?.apply {
            cardCustomer.setOnClickListener {
                //TODO when customer is selected
                navigateToLoginSignUpFragment()

            }
            cardAdmin.setOnClickListener {
                //TODO when admin is selected
                navigateToLoginSignUpFragment()

            }

        }
    }

    private fun navigateToLoginSignUpFragment() {
//        val action = UserTypeSelectionFragmentDirections.actionUserTypeSelectionFragmentToLoginSignupFragment()
//        findNavController().navigate(action)
    }
}