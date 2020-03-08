package com.boukharist.mvarchi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.form_view.*
import kotlinx.android.synthetic.main.info_view.*
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    lateinit var userApi : UserApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userApi = UserApi()
        populateData()
        setListeners()
    }

    private fun setListeners() {
        validateButton.setOnClickListener {
            val firstName = firstNameText.editText!!.text.toString()
            val lastName = lastNameText.editText!!.text.toString()
            val birthDateString = birthDateText.editText!!.text.toString()
            val user = User(firstName,lastName,birthDateString)
            setCurrentUser(user)
        }
    }

    private fun setCurrentUser(user: User) {
        userApi.setUser(user)
        populateData()
    }

    private fun populateData() {
        val user = userApi.getUser()
        setAge(user)
        setName(user)
    }

    private fun setName(user: User) {
        val fullName = user.getFullName()
        nameTextView.text = getString(R.string.full_name_placeholder, fullName)
    }

    private fun setAge(user: User) {
        val now = LocalDateTime.now()
        val age = user.getAge(now)
        ageTextView.text = getString(R.string.age_placeholder, age)
    }
}
