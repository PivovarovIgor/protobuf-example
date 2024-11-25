package ru.brauer.protobufex

import android.os.Bundle
import android.util.Base64
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codelab.android.datastore.UserPreferences

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val obj = UserPreferences
            .newBuilder()
            .setShowCompleted(true)
            .setSomeNum(Long.MAX_VALUE)
            .build()

        println("VVV toByteArray = ${obj.toByteArray().asUByteArray().map { it.toString(16) }}")
        Base64.encodeToString(obj.toByteArray(), Base64.DEFAULT)
            .also { println("VVV base64 = $it") }
    }
}