package ru.alekseyivashin.kotlinandroidtest.database

object UserTable {
    val NAME: String = "User"

    object Field {
        val ID: String = "id"
        val EMAIL: String = "email"
        val PASSWORD: String = "password"
    }
}