package ru.alekseyivashin.kotlinandroidtest.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.jetbrains.anko.startActivity
import ru.alekseyivashin.kotlinandroidtest.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addUser(view: View) {
        startActivity<AddUserActivity>()
    }
}
