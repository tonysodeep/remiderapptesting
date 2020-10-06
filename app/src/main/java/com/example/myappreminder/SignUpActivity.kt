package com.example.myappreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val loginPreferences = LoginPreferences(this)
        val context = this
        var db = UserDB(context)
        bt_sign_up.setOnClickListener {
            var user = UserData(
                sign_up_account_name.text.toString(),
                sign_up_password.text.toString(),
                sign_up_email.text.toString()
            )
            db.insertData(user)
            loginPreferences.setUser(db.selectSignUpUser())
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        read_data.setOnClickListener {
            var data = db.readData()
            for(i in 0 until data.size){
                Log.d("Data", data[i].id.toString()+" "+ data[i].accountName+ " "+data[i].accountPassword)
            }
        }
    }
}