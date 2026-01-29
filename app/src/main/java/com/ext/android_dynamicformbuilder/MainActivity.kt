package com.ext.android_dynamicformbuilder

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ext.dynamicform.core.FormBuilder
import com.ext.dynamicform.core.FormResult
import com.ext.dynamicform.utils.JsonParser
import com.ext.dynamicform.validation.Validator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val testJson = """
{
  "title": "Test Form",
  "fields": [
    {
      "type": "text",
      "key": "name",
      "label": "Full Name",
      "required": true
    },
    {
      "type": "email",
      "key": "email",
      "label": "Email",
      "required": true
    },
    {
      "type": "password",
      "key": "password",
      "label": "password",
      "required": true
    },
    {
      "type": "checkbox",
      "key": "terms",
      "label": "Accept Terms",
      "required": true
    },
    {
      "type": "checkbox_group",
      "key": "skills",
      "label": "Skills",
      "options": ["Kotlin", "Java", "Compose"]
    },
    {
      "type": "radio",
      "key": "experience",
      "label": "Experience",
      "options": ["Junior", "Mid", "Senior"]
    },
    {
      "type": "dropdown",
      "key": "gender",
      "label": "Gender",
      "options": ["Male", "Female", "Other"]
    }
  ]
}
""".trimIndent()

        val container = findViewById<LinearLayout>(R.id.formContainer)
        val submitBtn = findViewById<Button>(R.id.btnSubmit)

        val formConfig = JsonParser.parse(testJson)

        val builder = FormBuilder(this, container)
        builder.build(formConfig)

        submitBtn.setOnClickListener {
            val resultMap = builder.getResult()

            val errors = Validator.validate(formConfig.fields, resultMap)

            if (errors.isNotEmpty()) {
                Toast.makeText(this, errors.first(), Toast.LENGTH_SHORT).show()
            } else {
                val formResult = FormResult(resultMap)
                Toast.makeText(
                    this,
                    "Name: ${formResult.getString("name")}\nGender: ${formResult.getString("gender")}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}