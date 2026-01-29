package com.ext.dynamicform.validation

import com.ext.dynamicform.model.FieldConfig

object Validator {

    fun validate(
        fields: List<FieldConfig>,
        data: Map<String, Any?>
    ): List<String> {

        val errors = mutableListOf<String>()

        fields.forEach { field ->
            if (field.required) {
                val value = data[field.key]

                if (value == null || value.toString().isBlank()) {
                    errors.add("${field.label} is required")
                }
            }
        }

        return errors
    }
}
