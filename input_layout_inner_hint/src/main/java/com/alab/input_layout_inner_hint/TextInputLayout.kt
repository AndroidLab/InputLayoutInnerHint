package com.alab.input_layout_inner_hint

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import com.google.android.material.textfield.TextInputLayout

class TextInputLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    @DrawableRes
    var focusedDrawable: Int

    @DrawableRes
    var unfocusedDrawable: Int

    @DrawableRes
    var disabledDrawable: Int

    @DrawableRes
    var errorDrawable: Int

    private val editTextFocusChangeListeners = mutableListOf<OnFocusChangeListener>()

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextInputLayout)
        focusedDrawable = typedArray.getResourceId(
            R.styleable.TextInputLayout_textInputLayoutFocusedDrawable,
            R.drawable.text_input_white_background_focused_shape
        )
        unfocusedDrawable = typedArray.getResourceId(
            R.styleable.TextInputLayout_textInputLayoutUnfocusedDrawable,
            R.drawable.text_input_white_background_unfocused_shape
        )
        disabledDrawable = typedArray.getResourceId(
            R.styleable.TextInputLayout_textInputLayoutDisabledDrawable,
            R.drawable.text_input_white_background_disabled_shape
        )
        errorDrawable = typedArray.getResourceId(
            R.styleable.TextInputLayout_textInputLayoutErrorDrawable,
            R.drawable.text_input_white_background_error_shape
        )
        typedArray.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        editText!!.setOnFocusChangeListener { view, b ->
            editTextFocusChangeListeners.forEach {
                it.onFocusChange(view, b)
            }
            setEditTextDrawable()
        }
    }



    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        setEditTextDrawable()
    }

    override fun setError(errorText: CharSequence?) {
        super.setError(errorText)
        setEditTextDrawable()
    }

    /**
     * Добавляет слушателей изменения фокуса.
     * @param l Слушатель изменения фокуса.
     */
    fun addEditTextFocusChangeListener(l: OnFocusChangeListener) {
        editTextFocusChangeListeners.add(l)
    }

    /**
     * Удаляет слушателей изменения фокуса.
     * @param l Слушатель изменения фокуса.
     */
    fun removeEditTextFocusChangeListener(l: OnFocusChangeListener) {
        editTextFocusChangeListeners.remove(l)
    }

    private fun setEditTextDrawable() {
        editText?.let { editText ->
            if (error.isNullOrEmpty()) {
                if (editText.isFocused) {
                    editText.setBackgroundResource(focusedDrawable)
                } else {
                    editText.setBackgroundResource(unfocusedDrawable)
                }
            } else {
                editText.setBackgroundResource(errorDrawable)
            }
            if (!isEnabled) {
                editText.setBackgroundResource(disabledDrawable)
            }
        }
    }

}