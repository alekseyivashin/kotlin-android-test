package ru.alekseyivashin.kotlinandroidtest.database

import android.content.ContentValues
import android.content.Context
import org.jetbrains.anko.db.insert
import ru.alekseyivashin.kotlinandroidtest.models.User

object DBUtils {
    fun insertUser(ctx: Context, user: User) {
        ctx.database.use {
            val values = ContentValues()
            values.put(UserTable.Field.EMAIL, user.email)
            values.put(UserTable.Field.PASSWORD, user.password)
            insert(UserTable.NAME, null, values)
        }
    }
}