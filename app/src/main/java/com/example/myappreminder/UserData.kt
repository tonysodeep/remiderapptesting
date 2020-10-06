package com.example.myappreminder

class UserData {
    var id: Int = 0
    var accountName: String = ""
    var accountPassword: String = ""
    var accountEmail: String = ""

    constructor(accountName: String, accountPassword: String, accountEmail: String) {
        this.accountName = accountName
        this.accountPassword = accountPassword
        this.accountEmail = accountEmail
    }
    constructor(id :Int, accountName: String, accountPassword: String, accountEmail: String) {
        this.id  = id
        this.accountName = accountName
        this.accountPassword = accountPassword
        this.accountEmail = accountEmail
    }

    constructor(){

    }
}