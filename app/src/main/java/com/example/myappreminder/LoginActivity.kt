package com.example.myappreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val db = UserDB(this)
        val loginPreferences = LoginPreferences(this)
        bt_go_sign_up.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        bt_login.setOnClickListener {
            val userData = db.userPresent(et_account_name.text.toString(),password.text.toString())
            if(userData!=null){
                val intent = Intent(this,MainActivity::class.java)
                loginPreferences.setUser(userData)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Login fail",Toast.LENGTH_SHORT).show()
            }
        }
    }
}