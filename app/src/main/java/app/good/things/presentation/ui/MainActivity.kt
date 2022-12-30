package app.good.things.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.good.things.R

/**
 * Activity Login
 */
internal class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }
}