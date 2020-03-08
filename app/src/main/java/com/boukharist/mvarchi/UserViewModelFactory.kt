package com.boukharist.mvarchi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModelFactory :ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val userApi = UserApi.getInstance()
        return UserViewModel(userApi) as T
    }
}