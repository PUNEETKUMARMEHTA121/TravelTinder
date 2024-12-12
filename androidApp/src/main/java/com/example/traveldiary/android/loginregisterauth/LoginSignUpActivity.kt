package com.example.traveldiary.android.loginregisterauth


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.traveldiary.android.R
import com.example.traveldiary.android.common.base.BaseActivity
import com.example.traveldiary.android.landingScreen.HomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginSignUpActivity : BaseActivity() {
    //    private val firebaseConfigViewModel: FirebaseConfigViewModel by viewModel()
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    val loginViewModel: LoginViewModel by viewModel()
    val registerViewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (loginViewModel.isUserLoggedIn()) {
            openActivityAndFinishCurrent(HomeActivity::class.java)
        }
        navController.setGraph(R.navigation.login_signup_nav)
        auth = FirebaseAuth.getInstance()

        //        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.main_navigation) as? NavHostFragment
//        navHostFragment?.let {
//            val navController = it.navController
//            navController.addOnDestinationChangedListener { _, destination, _ ->
//                if (destination.id == R.id.btn_sign_In_with_google) {
//                    val signInIntent = googleSignInClient.signInIntent
//                    launcher.launch(signInIntent)
//                }
//            }
//        }
    }

//    private fun fetchLandingConfig() {
//        lifecycle.coroutineScope.launch {
//            firebaseConfigViewModel.landingConfig.collect { data ->
//                if (data.data.isNullOrEmpty().not()) {
//                    Log.d(
//                        "DebuggingCode: ",
//                        "firebaseConfigViewModel.landingConfig: " + Gson().toJson(data.data)
//                    )
////                    stateViewModel.landingConfig = data.data
//                    selectedAudioUrl =
//                        data.data?.find { it.languageCode.equals("en") }?.welcome_audio
//                    playAudio()
//                }
//            }
//        }
//    }

    private fun relaunchActivity() {
        val intent = Intent(this, LoginSignUpActivity::class.java)
        finish()
        startActivity(intent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (task.isSuccessful) {
                    val account = task.result
                    val idToken = account.idToken
                    updateUI(account)
//                googleSignInViewModel.signInWithGoogle(Intent().apply { putExtra("idToken", idToken,)
//                }.toString())
                } else {
                    Toast.makeText(this, "Google Sign-In Failed", Toast.LENGTH_SHORT).show()
                }
            } else if (result.resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Google Sign-In Canceled", Toast.LENGTH_SHORT).show()
            }
        }


    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Google Sign-In Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

}