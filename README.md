## Dynamic Form Builder (Android · Kotlin)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green)](LICENSE)
[![API](https://img.shields.io/badge/API-24%2B-orange)](#)
---

A lightweight, JSON-driven Dynamic Form Builder for Android that lets you create forms at runtime without writing XML for each screen.

---

### Features

- Build forms dynamically using JSON
- Material Design UI (TextInputLayout, Material Dropdown)
- Supports multiple field types
- Automatic input type mapping (email, number, password)
- Validation support (required fields)
- Clean result extraction (Map<String, Any?>)
- Library module (easy to reuse & publish)

---

## Installation (JitPack)

### 1️⃣ Add JitPack to your **root `settings.gradle` or `build.gradle`**

```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
### Add Dependency
```
dependencies {
	        implementation 'com.github.Excelsior-Technologies-Community:Android_EmptyStateWidgets:1.0.0'
	}
```

---

### Basic Usage

Add a container in your layout
```xml
<LinearLayout
    android:id="@+id/formContainer"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical" />
```

Define your form in JSON
```
{
  "title": "Signup Form",
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
      "label": "Password"
    },
    {
      "type": "dropdown",
      "key": "gender",
      "label": "Gender",
      "options": ["Male", "Female", "Other"]
    },
    {
      "type": "checkbox",
      "key": "terms",
      "label": "Accept Terms",
      "required": true
    }
  ]
}
```

Build the form in your Activity / Fragment
```kotlin
val formConfig = JsonParser.parse(json)

val builder = FormBuilder(this, binding.formContainer)
builder.build(formConfig)
```

Validate & get results
```kotlin
val resultMap = builder.getResult()

val errors = Validator.validate(formConfig.fields, resultMap)

if (errors.isNotEmpty()) {
    Toast.makeText(this, errors.first(), Toast.LENGTH_SHORT).show()
} else {
    val name = resultMap["name"] as String
    val email = resultMap["email"] as String
}
```

Result Data Format

Returned as:
```
Map<String, Any?>
```

Example:
```
{
  "name": "John",
  "email": "john@email.com",
  "gender": "Male",
  "terms": true,
  "skills": ["Kotlin", "Compose"]
}
```

Validation

Currently supported:

✅ Required fields ("required": true)

Empty or unchecked required fields are flagged

---

### Example Usage

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="@color/white"
    android:padding="16dp">

    <LinearLayout
        android:id="@+id/formContainer"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical" />

    <Button
        android:id="@+id/btnSubmit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Submit" />

</LinearLayout>
```

```kotlin

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
```

---

### Supported Field Types

| Type | Description | Result Type |
|-----|------------|-------------|
| `text` | Normal text input | `String` |
| `email` | Email keyboard | `String` |
| `number` | Numeric input | `String` |
| `password` | Password with visibility toggle | `String` |
| `dropdown` | Material dropdown | `String` |
| `checkbox` | Single checkbox | `Boolean` |
| `checkbox_group` | Multiple checkboxes | `List<String>` |
| `radio` | Radio group | `String` |

---

### License

```
MIT License

Copyright (c) 2025 Excelsior Technologies 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

