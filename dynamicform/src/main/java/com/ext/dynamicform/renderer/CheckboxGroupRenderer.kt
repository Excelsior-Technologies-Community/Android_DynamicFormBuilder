package com.ext.dynamicform.renderer

import android.content.Context
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import com.ext.dynamicform.model.FieldConfig

object CheckboxGroupRenderer {

    fun render(
        context: Context,
        parent: LinearLayout,
        field: FieldConfig,
        result: MutableMap<String, Any?>
    ) {
        val selectedItems = mutableListOf<String>()

        val title = TextView(context).apply {
            text = field.label
        }
        parent.addView(title)

        field.options?.forEach { option ->
            val checkBox = CheckBox(context).apply {
                text = option
            }

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedItems.add(option)
                } else {
                    selectedItems.remove(option)
                }
                result[field.key] = selectedItems.toList()
            }

            parent.addView(checkBox)
        }
    }
}
