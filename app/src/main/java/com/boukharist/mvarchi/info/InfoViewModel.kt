package com.boukharist.mvarchi.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.boukharist.mvarchi.DisplayableUser
import com.boukharist.mvarchi.IUserApi
import com.boukharist.mvarchi.User
import java.time.LocalDateTime

class InfoViewModel(private val userApi: IUserApi) : ViewModel() {

    val userLiveData = MutableLiveData<DisplayableUser>()

    private val userApiObserver = Observer<User> { user ->
        val now = LocalDateTime.now()
        val displayableUser = DisplayableUser(user.getFullName(), user.getAge(now))
        userLiveData.value = displayableUser
    }

    fun fetchData() {
      userApi.getUser().observeForever(userApiObserver)
    }

    override fun onCleared() {
        userApi.getUser().removeObserver(userApiObserver)
        super.onCleared()
    }
}
