package com.example.myappreminder

import android.content.Context

class LoginPreferences(context: Context) {
    val PREFERENCENAME = "SharePreferenceName"
    val PREFERENCE_ACCOUNT_NAME = "accName"
    val PREFERENCE_ACCOUNT_PASS = "accPass"
    val PREFERENCE_ACCOUNT_EMAIL = "accEmail"
    val PREFERNCE_ACCOUNT_ID = "accId"


    val preferences = context.getSharedPreferences(PREFERENCENAME, Context.MODE_PRIVATE)

    fun getUser(): UserData {
        val userId = preferences.getInt(PREFERNCE_ACCOUNT_ID,0)
        val userAccountName = preferences.getString(PREFERENCE_ACCOUNT_NAME, "")
        val userAccountPass = preferences.getString(PREFERENCE_ACCOUNT_PASS, "")
        val userAccountEmail = preferences.getString(PREFERENCE_ACCOUNT_EMAIL, "")
        return UserData(userId,userAccountName!!, userAccountPass!!, userAccountEmail!!)
    }

    fun setUser(userData: UserData) {
        val editor = preferences.edit()
        editor.apply {
            putInt(PREFERNCE_ACCOUNT_ID,userData.id)
            putString(PREFERENCE_ACCOUNT_NAME, userData.accountName)
            putString(PREFERENCE_ACCOUNT_PASS, userData.accountPassword)
            putString(PREFERENCE_ACCOUNT_EMAIL, userData.accountEmail)
            apply()
        }

    }
    fun clearUser(){
        val editor = preferences.edit()
        editor.apply{
            putInt(PREFERNCE_ACCOUNT_ID,0)
            putString(PREFERENCE_ACCOUNT_NAME, null)
            putString(PREFERENCE_ACCOUNT_PASS, null)
            putString(PREFERENCE_ACCOUNT_EMAIL, null)
            apply()
        }
    }
}