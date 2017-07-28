package ru.alekseyivashin.kotlinandroidtest.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_add_user.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import ru.alekseyivashin.kotlinandroidtest.R
import ru.alekseyivashin.kotlinandroidtest.database.DBUtils
import ru.alekseyivashin.kotlinandroidtest.models.User

/**
 * A login screen that offers login via email/password.
 */
class AddUserActivity : AppCompatActivity() {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private var mAuthTask: UserLoginTask? = null

    // UI references.
    private var mEmailView: AutoCompleteTextView? = null
    private var mPasswordView: EditText? = null
    private var mProgressView: View? = null
    private var mLoginFormView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        mEmailView = this.email
        mPasswordView = this.password

        mPasswordView!!.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == R.id.login || id == EditorInfo.IME_NULL) {
                addUser()
                return@OnEditorActionListener true
            }
            false
        })

        this.add_user_button.setOnClickListener { addUser() }

        mLoginFormView = this.login_form
        mProgressView = this.login_progress
    }

    private fun addUser() {
        if (mAuthTask != null) {
            return
        }

        // Reset errors.
        mEmailView!!.error = null
        mPasswordView!!.error = null

        // Store values at the time of the login attempt.
        val email: String = mEmailView!!.text.toString()
        val password: String = mPasswordView!!.text.toString()

        val validationResult = validateFields(email, password)
        if (validationResult != null) {
            val view = validationResult.first
            val message = validationResult.second

            view.error = message
            view.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            mAuthTask = UserLoginTask(email, password)
            mAuthTask!!.execute(null as Void?)
        }
    }

    private fun validateFields(email: String, password: String): Pair<TextView, String>? {
        val errorFieldRequired: String = getString(R.string.error_field_required)
        val errorEmailInvalid: String = getString(R.string.error_invalid_email)
        val errorPasswordInvalid: String = getString(R.string.error_invalid_password)

        return when {
            TextUtils.isEmpty(email) -> Pair(mEmailView as TextView, errorFieldRequired)
            TextUtils.isEmpty(password) -> Pair(mPasswordView as TextView, errorFieldRequired)
            !email.contains("@") -> Pair(mEmailView as TextView, errorEmailInvalid)
            password.length < 4 -> Pair(mPasswordView as TextView, errorPasswordInvalid)
            else -> null
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

            mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
            mLoginFormView!!.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
                }
            })

            mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
            mProgressView!!.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
                }
            })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
            mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    private inner class UserLoginTask internal constructor(private val mEmail: String, private val mPassword: String) : AsyncTask<Void, Void, Boolean>() {

        private val logger = AnkoLogger("USER")

        override fun doInBackground(vararg params: Void): Boolean? {
//            CREDENTIALS.add(User(mEmail, mPassword))

            DBUtils.insertUser(applicationContext, User(null, mEmail, mPassword))

            logger.info(CREDENTIALS)

            finish()
            return true
        }

        override fun onCancelled() {
            mAuthTask = null
            showProgress(false)
        }
    }

    companion object {
        private val CREDENTIALS: MutableList<User> = mutableListOf()
    }
}

