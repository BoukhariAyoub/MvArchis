package com.boukharist.mvarchi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.form_view.*
import kotlinx.android.synthetic.main.info_view.*
import java.time.LocalDateTime

class MainActivity : AppCompatActivity(), IView {

    private lateinit var controller: UserController
    private lateinit var userApi: IUserApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userApi = UserApi()
        controller =
            UserController(userApi, this)
        populateData()
        setListeners()
    }

    private fun setListeners() {
        validateButton.setOnClickListener {
            val firstName = firstNameText.editText!!.text.toString()
            val lastName = lastNameText.editText!!.text.toString()
            val birthDate = birthDateText.editText!!.text.toString()
            controller.onValidateClicked(firstName, lastName, birthDate)
        }
    }

    override fun populateData() {
        val user = userApi.getUser()
        showAge(user)
        showName(user)
    }

    private fun showName(user: User) {
        val fullName = user.getFullName()
        nameTextView.text = getString(R.string.full_name_placeholder, fullName)
    }

    private fun showAge(user: User) {
        val now = LocalDateTime.now()
        val age = user.getAge(now)
        ageTextView.text = getString(R.string.age_placeholder, age)
    }
}
