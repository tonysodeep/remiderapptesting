package com.example.myappreminder

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.security.AccessControlContext

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "User"
val COL_ID = "Id"
val COL_ACCOUNT_NAME = "Account_Name"
val COL_ACCOUNT_PASSWORD = "Account_Password"
val COL_ACCOUNT_EMAIL = "Account_Email"

class UserDB(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_ACCOUNT_NAME TEXT, $COL_ACCOUNT_PASSWORD TEXT, $COL_ACCOUNT_EMAIL TEXT )"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(user: UserData) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.apply {
            put(COL_ACCOUNT_NAME, user.accountName)
            put(COL_ACCOUNT_PASSWORD, user.accountPassword)
            put(COL_ACCOUNT_EMAIL, user.accountEmail)
        }
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong()) {
            Toast.makeText(context, "Fail", Toast.LENGTH_LONG).show()
            Log.d("result", result.toString())
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
            Log.d("result", result.toString())
        }
    }

    fun readData(): MutableList<UserData> {
        var list: MutableList<UserData> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var user = UserData()
                user.apply {
                    id = result.getInt(result.getColumnIndex(COL_ID))
                    accountName = result.getString(result.getColumnIndex(COL_ACCOUNT_NAME))
                    accountPassword = result.getString(result.getColumnIndex(COL_ACCOUNT_PASSWORD))
                    accountEmail = result.getString(result.getColumnIndex(COL_ACCOUNT_EMAIL))
                    list.add(user)
                }
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun userPresent(accountName: String, accountPass: String): UserData? {
        val db = this.readableDatabase
        val query =
            "SELECT * FROM $TABLE_NAME WHERE $COL_ACCOUNT_NAME LIKE '$accountName' AND $COL_ACCOUNT_PASSWORD LIKE '$accountPass'"
        val cursor = db.rawQuery(query, null)
        Log.d("Find", cursor.count.toString())
        if (cursor.count <= 0) {
            cursor.close()
            return null
        }
        return selectUser(cursor)
    }
    fun selectSignUpUser():UserData{
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME ORDER BY $COL_ID DESC LIMIT 1"
        val cursor = db.rawQuery(query,null)
        return selectUser(cursor)
    }
    fun selectUser(result: Cursor): UserData {
        val user = UserData()
        if (result.moveToFirst()) {
            do {
                user.apply {
                    id = result.getInt(result.getColumnIndex(COL_ID))
                    accountName = result.getString(result.getColumnIndex(COL_ACCOUNT_NAME))
                    accountPassword = result.getString(result.getColumnIndex(COL_ACCOUNT_PASSWORD))
                    accountEmail = result.getString(result.getColumnIndex(COL_ACCOUNT_EMAIL))
                }
            } while (result.moveToNext())
        }
        result.close()
        return user
    }
}