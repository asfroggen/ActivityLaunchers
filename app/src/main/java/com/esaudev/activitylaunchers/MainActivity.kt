package com.esaudev.activitylaunchers

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.esaudev.activitylaunchers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var searchResultActivity:ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.messageMain.setOnClickListener {
            goToSearch()
        }

        onSearchActivityResult()
    }

    private fun goToSearch() {
        searchResultActivity?.launch(Intent(this, SearchActivity::class.java))
    }

    private fun onSearchActivityResult() {
        searchResultActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data?.getStringExtra(Constants.SEARCH_EXTRA)
                binding.messageMain.text = data
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        searchResultActivity = null
    }
}