package com.ext.dynamicform.core

class FormResult(
    private val data: Map<String, Any?>
) {

    fun getString(key: String): String? {
        return data[key] as? String
    }

    fun getInt(key: String): Int? {
        return when (val value = data[key]) {
            is Int -> value
            is String -> value.toIntOrNull()
            else -> null
        }
    }

    fun getBoolean(key: String): Boolean? {
        return data[key] as? Boolean
    }

    fun asMap(): Map<String, Any?> = data
}
