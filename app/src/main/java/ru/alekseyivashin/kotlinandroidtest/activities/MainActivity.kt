package ru.alekseyivashin.kotlinandroidtest.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_item.view.*
import org.jetbrains.anko.startActivity
import ru.alekseyivashin.kotlinandroidtest.R
import ru.alekseyivashin.kotlinandroidtest.adapters.UserAdapter
import ru.alekseyivashin.kotlinandroidtest.database.DBUtils
import ru.alekseyivashin.kotlinandroidtest.common.onUserItemClick

class MainActivity : AppCompatActivity() {

    var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        this.menu = menu
        return true
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
        this.usersList.setOnItemClickListener { _, view, _, _ ->
            onUserItemClick(this, view.checkBox, !view.checkBox.isChecked) }
    }
}
