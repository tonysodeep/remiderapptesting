package com.example.myappreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginPreferences = LoginPreferences(this)
        var userData = loginPreferences.getUser()
        user_account.text = userData.accountName
        user_password.text = userData.accountPassword
        user_id.text = userData.id.toString()
        user_email.text = userData.accountEmail
        bt_logout.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            loginPreferences.clearUser()
        }
    }
}