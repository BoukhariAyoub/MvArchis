package com.boukharist.mvarchi.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boukharist.mvarchi.FormFields
import com.boukharist.mvarchi.IUserApi
import com.boukharist.mvarchi.User
import com.boukharist.mvarchi.UserApi

class FormViewModel(private val userApi: IUserApi) : ViewModel() {

    val formLiveData = MutableLiveData<FormFields>()

    fun onFormTextChanged(firstName: String, lastName: String, birthDate: String) {
        formLiveData.value = FormFields(firstName, lastName, birthDate)
    }

    fun onValidateClicked(firstName: String, lastName: String, birthDate: String) {
        val user = User(firstName, lastName, birthDate)
        userApi.setUser(user)
    }
}
