package ru.alekseyivashin.kotlinandroidtest.common

import android.content.Context
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.CompoundButton
import org.jetbrains.anko.toast
import ru.alekseyivashin.kotlinandroidtest.R
import ru.alekseyivashin.kotlinandroidtest.activities.MainActivity

fun onUserItemClick(context: Context, checkBoxView: CompoundButton, isChecked: Boolean) {
    context.toast("Клик по позиции с номером ${checkBoxView.tag}. Состояние: $isChecked")

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
//            menuItemAnimation(context, changeMenuItem, false)
            changeMenuItem.isVisible = false
        } else {
//            menuItemAnimation(context, deleteMenuItem, true)
//            menuItemAnimation(context, changeMenuItem, true)
            deleteMenuItem.isVisible = true
            changeMenuItem.isVisible = true
        }
    } else {
//        menuItemAnimation(context, deleteMenuItem, false)
//        menuItemAnimation(context, changeMenuItem, false)
        deleteMenuItem.isVisible = false
        changeMenuItem.isVisible = false
    }
}

object Static {
    val checkedUsersIds: MutableList<Int> = mutableListOf()
}