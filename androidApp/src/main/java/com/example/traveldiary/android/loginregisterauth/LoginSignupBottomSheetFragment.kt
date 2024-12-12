package com.example.traveldiary.android.loginregisterauth

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.traveldiary.android.R
import com.example.traveldiary.android.common.base.BaseActivity
import com.example.traveldiary.android.databinding.FragmentLoginSignUpBottomSheetBinding
import com.example.traveldiary.android.landingScreen.HomeActivity
import com.example.traveldiary.domain.model.LoginInputValidationType
import com.example.traveldiary.domain.model.RegisterInputValidationType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginSignupBottomSheetFragment : BottomSheetDialogFragment() {
//    private val userViewModel: UserViewModel by viewModel()
//    private val stateViewModel: StateViewModel by viewModel()

    private val passwordTransformationMethod = PasswordTransformationMethod.getInstance()


    private lateinit var binding: FragmentLoginSignUpBottomSheetBinding
    private var isFullScreen = false

    //    private lateinit var auth: FirebaseAuth
    private var shouldShowSignUp = false
    private var instanceId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginSignUpBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentLoginSignUpBottomSheetBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
//        auth = Firebase.auth
        if (shouldShowSignUp) {
            setVisibility(isSignUp = true)
        } else {
            setVisibility(isSignUp = false)
        }

//        binding.tvTermsCondition.setOnClickListener {
//            openWebView(Constants.TERMS_CONDITIONS, R.string.create_business_t_and_c)
//        }

//        binding.tvPolicy.setOnClickListener {
//            openWebView(Constants.PRIVACY_POLICY, R.string.create_business_privacy_policy)
//        }
//        binding.tvResetPassword.setOnClickListener {
//            showResetPasswordDialog()
//        }
        binding.btnCreateAccount.setOnClickListener {
            setupSignupButton()
        }
        binding.btnLogin.setOnClickListener {
            setupLoginButton()
        }
        binding.bottomSheetRootLayout?.setOnClickListener {
            fullScreen()
        }
        binding.imgShowHidePassword.setOnClickListener {
            showHidePass()
        }

        lifecycleScope.launch {
//            setInstanceId()
        }

        lifecycleScope.launch {
            (requireActivity() as LoginSignUpActivity).loginViewModel.loginMessage.collect { loginDetailsState ->
                when {
                    loginDetailsState.isLoading -> {
                        showLoading()
                    }

                    loginDetailsState.data != null -> {
                        hideLoading()
                        val userUid = FirebaseAuth.getInstance().currentUser?.uid
                        if (userUid != null) {
                            //TODO if user type is admin
                            //TODO show business setup flow if user type is admin and no busiess present else show business to select screen

                            //TODO if user type is customer
                            //TODO show customer flow

                            showToast("Login Successful")
                            (requireActivity() as BaseActivity).openActivityAndFinishCurrent(
                                HomeActivity::class.java
                            )
//                            userViewModel.fetchUser(userUid)
//                            userViewModel.fetchUser.collect { data ->
//                                if (data.data != null) {
//                                    navigateToHomeScreen()
//                                    Log.d("DebuggingCode: ", "infooo: " + Gson().toJson(data.data))
//                                    updateUserProfile(data.data)
//
//                                }
//                            }
                        }
                    }

                    loginDetailsState.error.isNotEmpty() -> {
                        showToast(loginDetailsState.error)
                        hideLoading()
                    }

                    else -> {
                        // Handle other cases
                    }
                }
            }
        }

        lifecycleScope.launch {
            (requireActivity() as LoginSignUpActivity).registerViewModel.registerMessage.collect { registerDetailsState ->
                when {
                    registerDetailsState.isLoading -> {
                        showLoading()
                    }

                    registerDetailsState.data != null -> {
                        hideLoading()
                        showToast("Registration Successfull")
                        (requireActivity() as BaseActivity).openActivityAndFinishCurrent(
                            HomeActivity::class.java
                        )
//                        val newUser = UserBusiness(
//                            oId = FirebaseAuth.getInstance().currentUser?.uid,
//                            cAt = System.currentTimeMillis(),
//                            uAt = System.currentTimeMillis(),
//                            name = binding.etName.text.toString().trim(),
//                            email = binding.etEmailId.text.toString().trim(),
//                            lastLoginDeviceId = "",//todo this
//                            wlId = null,
//                            installInstanceId = instanceId,
//                            createdFrom = "restokeep",
//                            currentTrafficFromIRA = stateViewModel.installReferral?.toMap(),
//                        )
//                        userViewModel.saveUser(newUser)
//                        userViewModel.saveUser.collect {
//                            if (it.data == true) {
//                                navigateToHomeScreen()
//                            }
//                        }

                    }

                    registerDetailsState.error.isNotEmpty() -> {
                        showToast(registerDetailsState.error)
                        hideLoading()
                    }

                    else -> {
                        // Handle other cases
                    }
                }
            }

        }

    }

    private fun showHidePass() {
        val customEditTextWithImg = binding.etPassword
        if (customEditTextWithImg.transformationMethod == passwordTransformationMethod) {
            customEditTextWithImg.transformationMethod = null
            binding.imgShowHidePassword.setImageResource(R.drawable.open_eye)
        } else {
            customEditTextWithImg.transformationMethod = passwordTransformationMethod
            binding.imgShowHidePassword.setImageResource(R.drawable.close_eye)
        }
        customEditTextWithImg.text?.length?.let { it1 -> customEditTextWithImg.setSelection(it1) }
    }

    private fun fullScreen() {
        if (isFullScreen) {
            dialog?.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            isFullScreen = false
        } else {
            dialog?.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            isFullScreen = true
        }
    }


    private fun setupSignupButton() {
        val name = binding.etName.text.toString()
        val email = binding.etEmailId.text.toString()
        val password = binding.etPassword.text.toString()

        val (validationType, errorMessage) = (requireActivity() as LoginSignUpActivity).registerViewModel.validateRegisterForm(
            name,
            email,
            password
        )
        if (errorMessage != null) {
            showToast(errorMessage)
            return
        }

        if (validationType == RegisterInputValidationType.Valid) {
            lifecycleScope.launch {
                (requireActivity() as LoginSignUpActivity).registerViewModel.registerUser(
                    name,
                    email,
                    password
                )
            }
        }
    }


    private fun setupLoginButton() {
        val emailEditText = binding.etEmailId
        val passwordEditText = binding.etPassword
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val (validationType, errorMessage) = (requireActivity() as LoginSignUpActivity).loginViewModel.validateLoginForm(
            email,
            password
        )

        if (errorMessage != null) {
            showToast(errorMessage)
            return
        }

        if (validationType == LoginInputValidationType.Valid) {
            lifecycleScope.launch {
                (requireActivity() as LoginSignUpActivity).loginViewModel.loginUser(email, password)
            }
        }
    }


//    private fun updateUserProfile(data: UserBusiness) {   //todo
//        val updatedUser = data.apply {
//            uAt = System.currentTimeMillis()
//            currentTrafficFromIRA = stateViewModel.installReferral?.toMap()
//            previousTrafficFromIRA = this.currentTrafficFromIRA
//        }
//
//        userViewModel.updateUser(updatedUser)
//
//        lifecycleScope.launch {
//            userViewModel.updateUser.collect { updateResp ->
//                if (updateResp.data == true) {
//                    navigateToHomeScreen()
//                } else {
//                    showToast("Failed to update user: ${updateResp.error}")
//                }
//            }
//        }
//    }

    private fun showToast(message: String) {
        val context = context
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading() {
        binding.loadingOverlay.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loadingOverlay.visibility = View.GONE
    }

//    private fun navigateToHomeScreen() {
//        if (isAdded) {
//            val intent = Intent(requireContext(), BusinessActivity::class.java)
//            startActivity(intent)
//            requireActivity().finish()
//        }
//    }

//    private fun openWebView(url: String, titleResId: Int) {
//        val i = Intent(requireContext(), WebViewActivity::class.java)
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        i.putExtra("url", url)
//        i.putExtra("title", titleResId)
//        i.putExtra("business", true)
//        startActivity(i)
//    }

    private fun setVisibility(isSignUp: Boolean) {
        val nameCardVisibility = if (isSignUp) View.VISIBLE else View.GONE
        val termsVisibility = if (isSignUp) View.VISIBLE else View.GONE
        val createAccountVisibility = if (isSignUp) View.VISIBLE else View.GONE
        val loginVisibility = if (isSignUp) View.GONE else View.VISIBLE
        val resetPasswordVisibility = if (isSignUp) View.GONE else View.VISIBLE

        binding.cardViewNameCard.visibility = nameCardVisibility
        binding.llTerms.visibility = termsVisibility
        binding.btnCreateAccount.visibility = createAccountVisibility
        binding.btnLogin.visibility = loginVisibility
        binding.tvResetPassword.visibility = resetPasswordVisibility
    }

    fun setVisibilityState(showSignUp: Boolean) {
        this.shouldShowSignUp = showSignUp
    }


//    private fun showResetPasswordDialog() {
//        val dialogFragment = ResetPasswordDialogFragment()
//        dialogFragment.show(childFragmentManager, "ResetPasswordDialog")
//    }

//    private fun setInstanceId() {
//        Analytics().getInstanceId(object : SimpleCallbackListener<String> {
//            override fun onSuccess(value: String) {
//                instanceId = value
//            }
//        })
//    }

}

