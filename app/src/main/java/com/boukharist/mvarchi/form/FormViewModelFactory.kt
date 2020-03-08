package com.boukharist.mvarchi.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boukharist.mvarchi.UserApi

class FormViewModelFactory :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val userApi = UserApi.getInstance()
        return FormViewModel(userApi) as T
    }
}