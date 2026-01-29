package com.ext.dynamicform.renderer

import android.content.Context
import android.text.InputType
import android.widget.LinearLayout
import androidx.core.widget.doAfterTextChanged
import com.ext.dynamicform.model.FieldConfig
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

object TextFieldRenderer {

    fun render(
        context: Context,
        parent: LinearLayout,
        field: FieldConfig,
        result: MutableMap<String, Any?>
    ) {
        val textInputLayout = TextInputLayout(context).apply {
            hint = field.label
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            // Password toggle
            if (field.type.equals("password", true)) {
                endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
            }
        }

        val editText = TextInputEditText(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            inputType = resolveInputType(field.type)
        }

        editText.doAfterTextChanged {
            result[field.key] = it?.toString()
        }

        textInputLayout.addView(editText)
        parent.addView(textInputLayout)
    }

    private fun resolveInputType(type: String): Int {
        return when (type.lowercase()) {
            "email" -> InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            "number" -> InputType.TYPE_CLASS_NUMBER
            "password" ->
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            else -> InputType.TYPE_CLASS_TEXT
        }
    }
}
