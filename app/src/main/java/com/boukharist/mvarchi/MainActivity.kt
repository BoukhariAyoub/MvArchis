package com.boukharist.mvarchi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.form_view.*
import kotlinx.android.synthetic.main.info_view.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, UserViewModelFactory()).get(UserViewModel::class.java)
        setListeners()
        initObservers()
        viewModel.fetchData()
    }

    private fun initObservers() {
        viewModel.userLiveData.observe(this, Observer {
            showAge(it.age)
            showName(it.fullName)
        })

        viewModel.formLiveData.observe(this, Observer {
            firstNameText.editText!!.setText(it.firstName)
            lastNameText.editText!!.setText(it.lastName)
            birthDateText.editText!!.setText(it.birthDate)
        })
    }

    override fun onDestroy() {
        val firstName = firstNameText.editText!!.text.toString()
        val lastName = lastNameText.editText!!.text.toString()
        val birthDate = birthDateText.editText!!.text.toString()
        viewModel.onFormTextChanged(firstName, lastName, birthDate)
        super.onDestroy()
    }

    private fun setListeners() {
        validateButton.setOnClickListener {
            val firstName = firstNameText.editText!!.text.toString()
            val lastName = lastNameText.editText!!.text.toString()
            val birthDate = birthDateText.editText!!.text.toString()
            viewModel.onValidateClicked(firstName, lastName, birthDate)
        }
    }

    private fun showName(fullName: String) {
        nameTextView.text = getString(R.string.full_name_placeholder, fullName)
    }

    private fun showAge(age: Int) {
        ageTextView.text = getString(R.string.age_placeholder, age)
    }
}
