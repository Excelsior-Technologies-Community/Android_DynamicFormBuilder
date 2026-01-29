package com.ext.dynamicform.renderer

import android.content.Context
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.ext.dynamicform.model.FieldConfig

object RadioRenderer {

    fun render(
        context: Context,
        parent: LinearLayout,
        field: FieldConfig,
        result: MutableMap<String, Any?>
    ) {
        val title = TextView(context).apply {
            text = field.label
        }
        parent.addView(title)

        val radioGroup = RadioGroup(context)

        field.options?.forEach { option ->
            val radioButton = RadioButton(context).apply {
                text = option
            }
            radioGroup.addView(radioButton)
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val selected = group.findViewById<RadioButton>(checkedId)
            result[field.key] = selected.text.toString()
        }

        parent.addView(radioGroup)
    }
}
