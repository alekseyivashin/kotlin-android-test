package ru.alekseyivashin.kotlinandroidtest.database

import android.content.ContentValues
import android.content.Context
import org.jetbrains.anko.db.*
import org.jetbrains.anko.toast
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

    fun deleteUsers(ctx: Context, ids: List<Int>) {
        val idsString = ids.joinToString()
        ctx.database.use {
            var result = execSQL("DELETE FROM ${UserTable.NAME} WHERE ID IN (${idsString})")
            ctx.toast("Удалено ${ids.size} пользователей")
        }
    }
}