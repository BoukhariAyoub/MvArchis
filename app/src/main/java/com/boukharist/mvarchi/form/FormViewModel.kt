package com.boukharist.mvarchi.form

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boukharist.mvarchi.FormFields
import com.boukharist.mvarchi.IUserApi
import com.boukharist.mvarchi.User

class FormViewModel(private val userApi: IUserApi) : ViewModel() {

    val firstName = ObservableField<String>()
    val lastName = ObservableField<String>()
    val birthDate = ObservableField<String>()

    fun onValidateClicked() {
        val user = User(firstName.get()!!, lastName.get()!!, birthDate.get()!!)
        userApi.setUser(user)
    }
}




