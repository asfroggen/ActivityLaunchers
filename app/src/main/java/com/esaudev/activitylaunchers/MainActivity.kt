package com.esaudev.activitylaunchers

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.esaudev.activitylaunchers.Constants.SEARCH_QUERY_KEY
import com.esaudev.activitylaunchers.databinding.ActivityMainBinding
import com.esaudev.activitylaunchers.databinding.ActivitySearchBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var resultSearchActivity: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.messageText.setOnClickListener {
            resultSearchActivity?.launch(Intent(this, SearchActivity::class.java))
        }

        onResultSearchActivity()
    }

    private fun onResultSearchActivity() {
        resultSearchActivity =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val query = result.data?.getStringExtra(SEARCH_QUERY_KEY) ?: String()
                    Toast.makeText(this, query, Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        resultSearchActivity = null
    }

}