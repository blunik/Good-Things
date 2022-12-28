package app.good.things.data

import android.content.Context
import android.content.SharedPreferences

/**
 * SharedPreference for app
 */
internal class SharedPrefs(context: Context) {
    private var APP_PREF_INDIVIDUAL_CODE = "individualCode"
    private var APP_NAME = "Good-Things"

    private val preferences: SharedPreferences =
        context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE)

    /**
     * Individual code for user
     */
    var individualCode: String?
        get() = preferences.getString(APP_PREF_INDIVIDUAL_CODE, "")
        set(value) = preferences.edit().putString(APP_PREF_INDIVIDUAL_CODE, value).apply()
}