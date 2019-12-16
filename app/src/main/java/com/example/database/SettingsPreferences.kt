package com.example.database

import android.content.Context
import android.content.Context.MODE_PRIVATE

class SettingsPreferences(appContext: Context) {
    companion object {
        private const val SETTINGS_PREF = "appJhonatan"
        const val DATABASE_NAME_KEY = "ASDASDAS"
    }

    private val pref = appContext.getSharedPreferences(SETTINGS_PREF, MODE_PRIVATE)
    fun setString(key: String, value: String) = pref.edit().putString(key, value).commit()
    fun string(key: String) = pref.getString(key, "")
}

val settingsPreferences by lazy { SettingsPreferences(App.getAppContext()) }