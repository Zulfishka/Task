package com.example.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref (context:Context) {

    private val pref = context.getSharedPreferences(SHARED_NAME, MODE_PRIVATE)


    fun isUserSeen(): Boolean{
        return pref.getBoolean(SEEN_KEY, false)
    }

    fun userSeen () {
        pref.edit().putBoolean(SEEN_KEY,true).apply()
    }

    fun saveName (name: String) {
        pref.edit().putString(SAVE_NAME_KEY,name).apply()
    }

    fun getName(): String? {
        return pref.getString(SAVE_NAME_KEY,"")
    }


    companion object {
        const val SHARED_NAME = "task_app"
        const val SEEN_KEY = "isSeen"
        const val SAVE_NAME_KEY = "name"
    }

}