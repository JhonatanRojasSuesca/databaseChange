package com.example.database

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.database.SettingsPreferences.Companion.DATABASE_NAME_KEY

class ManagerOpenHelperDatabase : SQLiteOpenHelper(App.getAppContext(), settingsPreferences.string(DATABASE_NAME_KEY), null, DATABASE_VERSION) {

    companion object {
        val DATABASE_VERSION = 1
        private var instance: ManagerOpenHelperDatabase? = null
        fun getInstance(): ManagerOpenHelperDatabase {
            if (instance == null)
                instance = ManagerOpenHelperDatabase()
            return instance!!
        }
    }
    fun updateDatabase(){
        this.close()
        instance = ManagerOpenHelperDatabase()
    }


    override fun onCreate(p0: SQLiteDatabase?) {
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun getReadable(): SQLiteDatabase = this.readableDatabase

}