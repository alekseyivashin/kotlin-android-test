package ru.alekseyivashin.kotlinandroidtest.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*
import ru.alekseyivashin.kotlinandroidtest.R

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        this.emailResult.text = intent.extras.getString("email")
    }
}
