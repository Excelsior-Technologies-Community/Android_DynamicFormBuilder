package com.ext.dynamicform.renderer

import android.content.Context
import android.widget.LinearLayout
import com.ext.dynamicform.model.FieldConfig

object FieldRenderer {

    fun render(
        context: Context,
        parent: LinearLayout,
        field: FieldConfig,
        result: MutableMap<String, Any?>
    ) {
        when (field.type.lowercase()) {
            "text", "email", "number", "password" ->
                TextFieldRenderer.render(context, parent, field, result)

            "dropdown" ->
                DropdownRenderer.render(context, parent, field, result)
        }
    }
}
