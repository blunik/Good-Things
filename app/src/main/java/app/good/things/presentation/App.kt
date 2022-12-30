package app.good.things.presentation

import android.app.Application
import app.good.things.data.SharedPrefs

/**
 * Local Shared Preferences
 */
internal val prefs: SharedPrefs by lazy {
    App.sharedPrefs!!
}

/**
 * Application
 */
internal class App : Application() {

    companion object {
        var sharedPrefs: SharedPrefs? = null
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        sharedPrefs = SharedPrefs(applicationContext)
    }
}