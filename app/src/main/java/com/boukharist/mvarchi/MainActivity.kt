package com.boukharist.mvarchi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.form_view.*
import kotlinx.android.synthetic.main.info_view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: UserViewModel = UserViewModel.create()
    private lateinit var displayableUserObserver: Observer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
        initObservers()
        viewModel.fetchData()
    }

    private fun initObservers() {
        displayableUserObserver = Observer { observable, _ ->
            if (observable is FieldObservable<*>) {
                val user = observable.getValue() as DisplayableUser
                showAge(user.age)
                showName(user.fullName)
            }
        }
        viewModel.getDisplayableUserObservable().addObserver(displayableUserObserver)
    }

    override fun onDestroy() {
        viewModel.getDisplayableUserObservable().deleteObserver(displayableUserObserver)
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
