package ru.alekseyivashin.kotlinandroidtest.common

import android.content.Context
import android.util.SparseBooleanArray
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.CompoundButton
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import ru.alekseyivashin.kotlinandroidtest.R
import ru.alekseyivashin.kotlinandroidtest.activities.MainActivity
import ru.alekseyivashin.kotlinandroidtest.database.DBUtils
import ru.alekseyivashin.kotlinandroidtest.models.User

fun onUserItemClick(context: Context, checkBoxView: CompoundButton, isChecked: Boolean) {
//    context.toast("Клик по позиции с номером ${checkBoxView.tag}. Состояние: $isChecked")

    checkBoxView.isChecked = isChecked

    if (isChecked) {
        Static.checkedUsersIds.add(checkBoxView.tag as Int)
    } else {
        Static.checkedUsersIds.remove(checkBoxView.tag as Int)
    }

    val deleteMenuItem: MenuItem = (context as MainActivity).menu!!.findItem(R.id.delete_user_menu_item)
    val changeMenuItem: MenuItem = context.menu!!.findItem(R.id.change_user_menu_item)

    if (Static.checkedUsersIds.size > 0) {
        if (Static.checkedUsersIds.size > 1) {
            changeMenuItem.isVisible = false
        } else {
            deleteMenuItem.isVisible = true
            changeMenuItem.isVisible = true
        }
    } else {
        deleteMenuItem.isVisible = false
        changeMenuItem.isVisible = false
    }
}

fun onDeleteMenuItemClick(context: Context): Boolean {
    DBUtils.deleteUsers(context, Static.checkedUsersIds)
    return true
}

object Static {
    val checkedUsersIds: MutableList<Int> = mutableListOf()
}