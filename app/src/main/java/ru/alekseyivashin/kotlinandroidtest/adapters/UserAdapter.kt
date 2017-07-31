package ru.alekseyivashin.kotlinandroidtest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.user_item.view.*
import org.jetbrains.anko.toast
import ru.alekseyivashin.kotlinandroidtest.R
import ru.alekseyivashin.kotlinandroidtest.models.User

class UserAdapter(val context: Context, val users: List<User>) : BaseAdapter() {

    val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: layoutInflater.inflate(R.layout.user_item, parent, false)

        val user: User = getItem(position) as User

        view.userEmail.text = user.email
        view.userPassword.text = user.password

        view.checkBox.tag = position
        view.checkBox.setOnCheckedChangeListener(onCheckBoxClick)

        return view
    }

    override fun getItem(position: Int): Any = users[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = users.size

    val onCheckBoxClick = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        context.toast("Клик по позиции с номером ${buttonView.tag}. Состояние: $isChecked")
        checkedUsersIds.add(buttonView.tag as Int)
    }

    companion object {
        val checkedUsersIds: MutableList<Int> = mutableListOf()
    }

}
