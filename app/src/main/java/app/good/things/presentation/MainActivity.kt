package app.good.things.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.good.things.R

/**
 * Activity Login
 */
internal class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Check existence individual code
        if (prefs.individualCode.isNullOrEmpty()) {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commitNow()
            }
        } else {

        }
    }
}