package com.esaudev.activitylaunchers

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.esaudev.activitylaunchers.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonSend.setOnClickListener {
            sendDataBack()
        }
    }

    private fun sendDataBack() {
        intent.putExtra(Constants.SEARCH_EXTRA, binding.messageText.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}