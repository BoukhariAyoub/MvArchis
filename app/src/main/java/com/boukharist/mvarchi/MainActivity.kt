package com.boukharist.mvarchi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.info_view.*

class MainActivity : AppCompatActivity() {

    lateinit var userApi : UserApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userApi = UserApi()
        populateData()
    }

    private fun populateData() {
        val user = userApi.getUser()
        showName(user)
    }

    private fun showName(user: User) {
        val fullName = user.getFullName()
        nameTextView.text = getString(R.string.full_name_placeholder, fullName)
    }
}
