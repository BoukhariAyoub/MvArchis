package com.boukharist.mvarchi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.form_view.*
import kotlinx.android.synthetic.main.info_view.*

class MainActivity : AppCompatActivity(), IView {

    private lateinit var presenter: IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
        presenter.onLoad()
        setListeners()
    }

    private fun initPresenter() {
        presenter = UserPresenter.create(this)
    }

    private fun setListeners() {
        validateButton.setOnClickListener {
            val firstName = firstNameText.editText!!.text.toString()
            val lastName = lastNameText.editText!!.text.toString()
            val birthDate = birthDateText.editText!!.text.toString()
            presenter.onValidateClicked(firstName, lastName, birthDate)
        }
    }

    override fun populateData(fullName: String, age: Int) {
        showAge(age)
        showName(fullName)
    }

    private fun showName(fullName: String) {
        nameTextView.text = getString(R.string.full_name_placeholder, fullName)
    }

    private fun showAge(age: Int) {
        ageTextView.text = getString(R.string.age_placeholder, age)
    }

}
