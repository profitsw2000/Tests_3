package com.geekbrains.tests.view.experimental

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geekbrains.tests.R
import com.geekbrains.tests.view.details.DetailsActivity

class ExperimentalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experimental)
    }

    companion object {

        const val TOTAL_COUNT = "TOTAL_COUNT_EXTRA"

        fun getIntent(context: Context, totalCount: Int): Intent {
            return Intent(context, ExperimentalActivity::class.java).apply {
                putExtra(TOTAL_COUNT, totalCount)
            }
        }
    }
}