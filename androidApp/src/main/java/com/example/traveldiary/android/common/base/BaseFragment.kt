package com.example.traveldiary.android.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * A generic base fragment class providing common functionality for fragments in the app.
 * @param VB The type of ViewBinding used by the fragment.
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    // ViewBinding instance for the fragment layout
     var binding: VB? = null

    /**
     * Abstract method to be implemented by child classes for inflating the ViewBinding.
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container The parent view that the fragment's UI should be attached to.
     * @return An instance of the ViewBinding for the fragment.
     */
    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the ViewBinding and return its root view
        binding = inflateBinding(inflater, container)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Perform initial setup when the view is created
        fetchData()
        setUpViews()
        setUpListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear the reference to the ViewBinding when the view is destroyed
        binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save UI data or states if needed
        saveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Restore UI data or states if needed
        restoreInstanceState(savedInstanceState)
    }

    /**
     * Override this method in child fragments to perform any data fetching operations.
     */
    abstract fun fetchData()

    /**
     * Override this method in child fragments to set up views after the view is created.
     */
    abstract fun setUpViews()

    /**
     * Override this method in child fragments to set up any event listeners or interactions.
     */
    abstract fun setUpListeners()

    /**
     * Override this method in child fragments to save their specific UI data or states.
     * @param outState The Bundle in which to place the saved state.
     */
    protected open fun saveInstanceState(outState: Bundle) {
        // Save fragment-specific data if needed
    }

    /**
     * Override this method in child fragments to restore their specific UI data or states.
     * @param savedInstanceState The saved instance state from which to restore.
     */
    protected open fun restoreInstanceState(savedInstanceState: Bundle?) {
        // Restore fragment-specific data if needed
    }
}
