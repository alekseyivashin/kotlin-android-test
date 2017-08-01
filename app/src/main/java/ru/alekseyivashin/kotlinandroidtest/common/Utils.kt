package ru.alekseyivashin.kotlinandroidtest.common

import android.content.Context
import android.view.MenuItem
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import ru.alekseyivashin.kotlinandroidtest.R

fun menuItemAnimation(context: Context, item: MenuItem, toShow: Boolean) {
//    val alphaValue: Float = if (toShow) 1F else 0F
//    val animation = AlphaAnimation(1 - alphaValue, alphaValue)
//    animation.duration = 1000
//    animation.repeatCount = Animation.INFINITE
//    item.actionView.startAnimation(animation)
    item.isVisible = toShow
}