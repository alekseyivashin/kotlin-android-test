package ru.alekseyivashin.kotlinandroidtest.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import ru.alekseyivashin.kotlinandroidtest.R
import ru.alekseyivashin.kotlinandroidtest.adapters.UserAdapter
import ru.alekseyivashin.kotlinandroidtest.database.DBUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        populateUsersList()
    }

    fun addUser(view: View) {
        startActivity<AddUserActivity>()
    }

    fun populateUsersList() {
        val users = DBUtils.getAllUsers(applicationContext)
        val userAdapter = UserAdapter(this, users)
        this.usersList.adapter = userAdapter
    }
}
