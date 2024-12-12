package com.example.traveldiary.android.common.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.example.traveldiary.android.R
import com.example.traveldiary.android.databinding.BaseDialogBoxBinding

class BaseDialogBox(
    context: Context
) : Dialog(context) {

    private val binding: BaseDialogBoxBinding by lazy {
        BaseDialogBoxBinding.inflate(layoutInflater)
    }

    private var customCancelButtonText: String? = null
    private var customConfirmButtonText: String? = null

    // Set default button names
    private val defaultCancelButtonText: String by lazy {
        context.getString(R.string.cancel_btn)
    }

    private val defaultConfirmButtonText: String by lazy {
        context.getString(R.string.confirm_btn)
    }

    private fun setElevation(elevation: Float) {
        val dialogView = window?.decorView
        ViewCompat.setElevation(dialogView!!, elevation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setBackground()
        setWindowSize()
        setButtonNames()
        setElevation(6f)
    }

    // Function to set the Width, Height of Dialog Box
    // Current is 90 percent of screen width

    private fun setWindowSize() {
        // Set dialog width and height
        val windowParams: WindowManager.LayoutParams = window?.attributes ?: return
        val screenWidth = context.resources.displayMetrics.widthPixels
        // Set width to 90% of the screen
        windowParams.width = (screenWidth * 0.9).toInt()
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = windowParams
    }

    // Function to set the Background

    private fun setBackground() {
        // Set background drawable
        val drawableResId = R.drawable.rounded_dialog_bg
        window?.setBackgroundDrawableResource(drawableResId)
    }

    // Function to set the title
    fun setTitle(newTitle: String) {
        binding.title.text = newTitle
    }

    // Function to set whether two double buttons are needed
    fun setTwoDoubleNeeded(isNeeded: Boolean) {
        binding.cancelBtn.visibility = if (isNeeded) View.VISIBLE else View.GONE
    }

    // Function to set whether description UI is needed
    fun setDescriptionVisibility(isNeeded: Boolean) {
        if (isNeeded) {
            binding.description.visibility = View.VISIBLE
        } else {
            binding.description.visibility = View.GONE
        }
    }

    // Function to set whether description UI is needed
    fun setContainerVisibility(isNeeded: Boolean) {
        if (isNeeded) {
            binding.container.visibility = View.VISIBLE
        } else {
            binding.container.visibility = View.GONE
        }
    }

    // Function to set the click listener for the confirm button
    fun setConfirmClickListener(listener: () -> Unit) {
        binding.confirmBtn.setOnClickListener {
            listener.invoke()
            dismiss()
        }
    }

    // Function to set the click listener for the cancel button
    fun setCancelClickListener(listener: () -> Unit) {
        binding.cancelBtn.setOnClickListener {
            listener.invoke()
            dismiss()
        }
    }

    //Function to set the Description message
    fun setDescriptionMessage(desc: String) {
        binding.description.text = desc
    }

    // Function to set custom button names
    fun setCustomButtonNames(cancelButtonText: String, confirmButtonText: String) {
        customCancelButtonText = cancelButtonText
        customConfirmButtonText = confirmButtonText
    }

    // Inside onCreate, set button names
    private fun setButtonNames() {
        binding.cancelBtn.text = customCancelButtonText ?: defaultCancelButtonText
        binding.confirmBtn.text = customConfirmButtonText ?: defaultConfirmButtonText
    }

    // Function to add XML layout to FrameLayout
    fun addXmlLayoutToFrame(xmlLayoutResId: Int) {
        val inflater = LayoutInflater.from(context)
        val contentView = inflater.inflate(xmlLayoutResId, null)
        binding.container.addView(contentView)
    }

    // Function to add view
    fun addDynamicView(view: View) {
        binding.container.addView(view)
    }

    // Function to add Fragment to FrameLayout
    fun addFragmentToFrame(fragment: Fragment) {
        val transaction =
            (context as? AppCompatActivity)?.supportFragmentManager?.beginTransaction()
        transaction?.replace(binding.container.id, fragment)
        transaction?.commit()
    }
}