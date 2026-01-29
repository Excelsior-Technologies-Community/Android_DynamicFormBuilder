package com.ext.dynamicform.utils

import com.ext.dynamicform.model.FormConfig
import com.google.gson.Gson

object JsonParser {
    fun parse(json: String): FormConfig {
        return Gson().fromJson(json, FormConfig::class.java)
    }
}
