package com.ext.dynamicform.model

data class FieldConfig(
    val type: String,
    val key: String,
    val label: String,
    val required: Boolean = false,
    val options: List<String>? = null
)
