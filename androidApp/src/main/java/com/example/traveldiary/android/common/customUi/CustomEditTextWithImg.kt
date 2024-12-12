package com.example.traveldiary.android.common.customUi

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.traveldiary.android.R

class CustomEditTextWithImg: AppCompatEditText {

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        initTextListener(this)
    }

    fun trimmedText(): String = text?.toString()?.trim() ?: ""
}

class CustomTextViewWithImg: AppCompatTextView {

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        initTextListener(this)
    }

    fun trimmedText() = text?.toString()?.trim() ?: ""
}

private fun initTextListener(textView: TextView) {
    textView.apply {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateDrawableColor(this@apply)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        updateDrawableColor(this)
    }
}

private fun updateDrawableColor(textView: TextView) {
    textView.apply {
        val drawable = compoundDrawablesRelative[0]
        val color = if (text.isNullOrEmpty()) {
            ContextCompat.getColor(context, R.color.material_grey400)
        } else {
            ContextCompat.getColor(context, R.color.dk_blue)
        }
        val colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
        val wrappedDrawable = DrawableCompat.wrap(drawable).mutate()
        wrappedDrawable.colorFilter = colorFilter
        setCompoundDrawablesRelativeWithIntrinsicBounds(wrappedDrawable, null, null, null)
    }
}
