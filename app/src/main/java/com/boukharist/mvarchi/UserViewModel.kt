package com.boukharist.mvarchi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime

class UserViewModel(private val userApi: IUserApi) : ViewModel() {

    val userLiveData = MutableLiveData<DisplayableUser>()
    val formLiveData = MutableLiveData<FormFields>()

    fun onFormTextChanged(firstName: String, lastName: String, birthDate: String) {
        formLiveData.value = FormFields(firstName, lastName, birthDate)
    }

    fun onValidateClicked(firstName: String, lastName: String, birthDate: String) {
        val user = User(firstName, lastName, birthDate)
        userApi.setUser(user)
        fetchData()
    }

    fun fetchData() {
        val user = userApi.getUser()
        val now = LocalDateTime.now()
        val displayableUser = DisplayableUser(user.getFullName(), user.getAge(now))
        userLiveData.value = displayableUser
    }
}