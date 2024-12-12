package com.example.traveldiary.android.loginregisterauth

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Handler
import com.example.traveldiary.android.R
import com.example.traveldiary.android.common.base.BaseFragment
import com.example.traveldiary.android.databinding.ActivityLoginSignUpBinding
import com.example.traveldiary.android.onboarding.PlaceHolderAdapter
import com.example.traveldiary.android.onboarding.SectionsPagerAdapter
import com.example.traveldiary.utils.Common

class LoginSignUpFragment : BaseFragment<ActivityLoginSignUpBinding>() {
    companion object {
        private const val AUTO_SCROLL_DELAY = 3000L
    }

//    var mp: MediaPlayer? = null
//private var mute = false
    private lateinit var viewAdapter: PlaceHolderAdapter

    private val autoScrollHandler = Handler()
    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            val currentItem = binding?.viewPager?.currentItem ?: 0
            val totalItems = binding?.viewPager?.adapter?.count ?: 0
            val nextItem = (currentItem + 1) % totalItems
            binding?.viewPager?.currentItem = nextItem
            autoScrollHandler.postDelayed(this, AUTO_SCROLL_DELAY)
        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ActivityLoginSignUpBinding {
        return ActivityLoginSignUpBinding.inflate(inflater, container, false)
    }

    override fun fetchData() {
    }

    override fun setUpViews() {
    }

    override fun setUpListeners() {
        // Initialize viewAdapter and viewPager here
        binding?.let { binding ->
            viewAdapter = PlaceHolderAdapter(
                requireContext(),
                parentFragmentManager,
                Common.getIntroImages().size
            )
            binding.viewPager.adapter = viewAdapter
            initUI()

//        if (sharedPrefs.getLanguageCode().isEmpty()) launchLanguageSelection()
//        InstallReferralHelper(this, object : CallbackListener<InstallReferral> {
//            override fun setObject(value: InstallReferral) {
//                stateViewModel.installReferral = value
//            }
//        })

//        fetchLandingConfig()

            binding.tvSignUp.setOnClickListener {
                showBottomSheet(shouldShowSignUp = true)
            }

            binding.btnSignInWithGmail.setOnClickListener {
                showBottomSheet(shouldShowSignUp = false)
            }

            binding.imglanguage.setOnClickListener {
//            launchLanguageSelection()
            }

//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.web_client_id)).requestEmail().build()
//        googleSignInClient = GoogleSignIn.getClient(this, gso)

            binding.btnSignInWithGoogle.setOnClickListener {
//            val signInIntent = googleSignInClient.signInIntent
//            launcher.launch(signInIntent)
            }

//            binding.volumeButton.setOnClickListener {
//                if (mute) {
//                    binding.mute.setImageResource(R.drawable.ic_volume_up)
//                    mp?.start()
//                } else {
                    binding.mute.setImageResource(R.drawable.ic_volume_off)
//                    mp?.pause()
//                }
//                mute = !mute
//            }
        }
    }

    private fun showBottomSheet(shouldShowSignUp: Boolean = false) {
        val bottomSheetFragment = LoginSignupBottomSheetFragment()
        bottomSheetFragment.setVisibilityState(shouldShowSignUp)
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    private fun initUI() {
        val sectionsPagerAdapter = SectionsPagerAdapter(
            requireContext(),
            parentFragmentManager,
            Common.getIntroImages().size
        )
        binding?.viewPager?.adapter = sectionsPagerAdapter
//        mp = MediaPlayer()
    }

    override fun onResume() {
        super.onResume()
        startAutoScroll()
    }

    override fun onPause() {
        super.onPause()
        stopAutoScroll()
    }

    private fun startAutoScroll() =
        autoScrollHandler.postDelayed(autoScrollRunnable, AUTO_SCROLL_DELAY)

    private fun stopAutoScroll() =
        autoScrollHandler.removeCallbacks(autoScrollRunnable)

}