package com.boukharist.mvarchi

import java.time.LocalDateTime


interface IPresenter {
    fun onValidateClicked(firstName: String, lastName: String, birthDate: String)
    fun onLoad()
}

class UserPresenter(private val userApi: IUserApi, private val view: IView) : IPresenter {

    override fun onValidateClicked(firstName: String, lastName: String, birthDate: String) {
        val user = User(firstName, lastName, birthDate)
        userApi.setUser(user)
        fetchUserAndNotify()
    }

    override fun onLoad() {
        fetchUserAndNotify()
    }

    private fun fetchUserAndNotify() {
        val user = userApi.getUser()
        val now = LocalDateTime.now()
        view.populateData(user.getFullName(), user.getAge(now))
    }

    companion object {
        fun create(view: IView): UserPresenter {
            val userApi = UserApi()
            return UserPresenter(userApi, view)
        }
    }
}