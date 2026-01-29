package com.ext.dynamicform.core

import android.content.Context
import android.widget.LinearLayout
import com.ext.dynamicform.model.FormConfig
import com.ext.dynamicform.renderer.FieldRenderer

class FormBuilder(
    private val context: Context,
    private val container: LinearLayout
) {
    private val resultMap = mutableMapOf<String, Any?>()

    fun build(formConfig: FormConfig) {
        container.removeAllViews()

        formConfig.fields.forEach { field ->
            FieldRenderer.render(context, container, field, resultMap)
        }
    }

    fun getResult(): Map<String, Any?> = resultMap
}
