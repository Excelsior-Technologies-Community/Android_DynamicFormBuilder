package com.ext.dynamicform.renderer

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.ext.dynamicform.model.FieldConfig
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout

object DropdownRenderer {

    fun render(
        context: Context,
        parent: LinearLayout,
        field: FieldConfig,
        result: MutableMap<String, Any?>
    ) {
        val textInputLayout = TextInputLayout(context).apply {
            hint = field.label
            endIconMode = TextInputLayout.END_ICON_DROPDOWN_MENU
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val autoCompleteTextView = MaterialAutoCompleteTextView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val options = field.options ?: emptyList()

        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_list_item_1,
            options
        )

        autoCompleteTextView.setAdapter(adapter)

        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            result[field.key] = options[position]
        }

        textInputLayout.addView(autoCompleteTextView)
        parent.addView(textInputLayout)
    }
}
