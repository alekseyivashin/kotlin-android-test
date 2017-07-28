package ru.alekseyivashin.kotlinandroidtest.database

import android.content.ContentValues
import android.content.Context
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
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

    fun getAllUsers(ctx: Context): List<User> {
        return ctx.database.use {
            select(UserTable.NAME).exec {
                parseList(classParser<User>())
            }
        }
    }
}