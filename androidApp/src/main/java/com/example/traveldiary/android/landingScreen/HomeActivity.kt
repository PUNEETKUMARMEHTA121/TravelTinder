package com.example.traveldiary.android.landingScreen

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.traveldiary.android.R
import com.example.traveldiary.android.common.base.BaseActivity
import com.example.traveldiary.android.common.base.BaseDialogBox
import com.example.traveldiary.android.databinding.ActivityHomeBinding
import com.example.traveldiary.android.logout.SignOutViewModel
import com.google.android.material.navigation.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var mNavController: NavController
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private val signOutViewModel: SignOutViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
        setupDrawer()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        if (navHostFragment == null) {
            // Add debug log if NavHostFragment is null
            throw NullPointerException("NavHostFragment is null")
        } else {
            mNavController = navHostFragment.navController
            mNavController.setGraph(R.navigation.nav_graph_home)
            binding.bottomNavigationView.setupWithNavController(mNavController)
        }
    }

    private fun setupDrawer() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.config_app_name)
        drawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_add_trip -> {
                // Handle navigation to item one
                true
            }

            R.id.nav_trip_request_status -> {
                // Handle navigation to item one
                true
            }

            R.id.nav_discover_by_place -> {
                // Handle navigation to item one
                true
            }

            R.id.nav_sign_out -> {
                // Handle navigation for sign_out
                showConfirmationDialog(
                    getString(R.string.confirmation),
                    getString(R.string.sign_out_confirmation_message)
                )
                true
            }
            // Add more items as needed
            else -> false
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun showConfirmationDialog(
        title: String,
        description: String,
    ) {
        BaseDialogBox(this).apply {
            setTitle(title)
            setDescriptionMessage(description)
            setConfirmClickListener {
                signOutViewModel.signOut()
                // Close the application
                finishAffinity()
            }
            setCancelClickListener {
            }
        }.show()
    }
}
