package com.ext.dynamicform.renderer

import android.content.Context
import android.widget.CheckBox
import android.widget.LinearLayout
import com.ext.dynamicform.model.FieldConfig

object CheckboxRenderer {

    fun render(
        context: Context,
        parent: LinearLayout,
        field: FieldConfig,
        result: MutableMap<String, Any?>
    ) {
        val checkBox = CheckBox(context).apply {
            text = field.label
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            result[field.key] = isChecked
        }

        parent.addView(checkBox)
    }
}
