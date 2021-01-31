package com.example.assignmentuserlist.sharedprefrences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MySharedPrefrences @Inject constructor(@ApplicationContext context: Context) {
    val prefs = context.getSharedPreferences("MyPrefrences", Context.MODE_PRIVATE)!!

    fun getString(id : String): String {
        return prefs.getString(id, "")!!
    }

    fun addString(id: String, value: String) {
        prefs.edit().putString(id, value).apply()
    }
}
