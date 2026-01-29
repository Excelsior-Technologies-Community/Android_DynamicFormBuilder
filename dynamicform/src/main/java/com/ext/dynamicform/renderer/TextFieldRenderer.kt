package com.ext.dynamicform.renderer

import android.content.Context
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.widget.doAfterTextChanged
import com.ext.dynamicform.model.FieldConfig

object TextFieldRenderer {

    fun render(
        context: Context,
        parent: LinearLayout,
        field: FieldConfig,
        result: MutableMap<String, Any?>
    ) {
        val editText = EditText(context).apply {
            hint = field.label
        }

        editText.doAfterTextChanged { text ->
            result[field.key] = text?.toString()
        }

        parent.addView(editText)
    }
}
