package com.example.traveldiary.android.common.base

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.traveldiary.android.databinding.BaseActivityBinding

/**
 * Base activity class providing common functionality for activities in the app.
 */
abstract class BaseActivity : AppCompatActivity() {

    // View binding instance for the activity layout
    private val binding: BaseActivityBinding by lazy {
        BaseActivityBinding.inflate(layoutInflater)
    }
    lateinit var navController: NavController

    /**
     * Abstract function to be implemented by subclasses to create the initial fragment.
     * @return The initial fragment to be displayed.
     */
//    protected abstract fun createInitialFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restore the state of fragments
        restoreFragmentState(savedInstanceState)
    }

    /**
     * Restore the state of fragments during activity restoration.
     * @param savedInstanceState The saved instance state bundle.
     */
    @SuppressLint("CommitTransaction")
    private fun restoreFragmentState(savedInstanceState: Bundle) {
        for (fragment in supportFragmentManager.fragments) {
            val fragmentTag = fragment::class.java.simpleName
            val savedFragment = supportFragmentManager.getFragment(savedInstanceState, fragmentTag)

            if (savedFragment != null) {
                // Replace the saved fragment in the container
                supportFragmentManager.beginTransaction()
                    .replace(getFragmentContainerId(), savedFragment)
                    .commit()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the state of fragments
        saveFragmentState(outState)
    }

    /**
     * Save the state of fragments during activity state save.
     * @param outState The bundle to save the fragment states.
     */
    private fun saveFragmentState(outState: Bundle) {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment.isAdded) {
                // Save each added fragment to the bundle
                supportFragmentManager.putFragment(
                    outState,
                    fragment::class.java.simpleName,
                    fragment
                )
            }
        }
    }

    /**
     * Get the ID of the fragment container.
     * @return The resource ID of the fragment container.
     */
    private fun getFragmentContainerId(): Int = binding.fragmentContainer.id

    /**
     * Replace the current fragment in the container.
     * @param fragment The fragment to be replaced.
     * @param addToBackStack Flag indicating whether to add the transaction to the back stack.
     */
    @SuppressLint("CommitTransaction")
    protected fun replaceFragment(
        fragment: Fragment,
        addToBackStack: Boolean = false,
        tag: String? = null
    ) {
        supportFragmentManager.beginTransaction()
            .replace(getFragmentContainerId(), fragment)
            .apply {
                if (addToBackStack) {
                    addToBackStack(tag)
                }
            }
            .commit()
    }

    /**
     * Add a fragment to the container.
     * @param fragment The fragment to be added.
     * @param addToBackStack Flag indicating whether to add the transaction to the back stack.
     */
    @SuppressLint("CommitTransaction")
    protected fun addFragment(
        fragment: Fragment,
        addToBackStack: Boolean = false,
        tag: String? = null
    ) {
        supportFragmentManager.beginTransaction()
            .add(getFragmentContainerId(), fragment).apply {
                if (addToBackStack) {
                    addToBackStack(tag)
                }
            }
            .commit()
    }

    // Function to open another activity and finish the current activity
    fun openActivityAndFinishCurrent(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
        finish()
    }

    // Function to open another activity without finishing the current activity
    fun openActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}