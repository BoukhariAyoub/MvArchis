package com.boukharist.mvarchi

import java.time.LocalDateTime

class UserViewModel(private val userApi: IUserApi) {

    private val userObservable: FieldObservable<DisplayableUser> = FieldObservable()

    fun onValidateClicked(firstName: String, lastName: String, birthDate: String) {
        val user = User(firstName, lastName, birthDate)
        userApi.setUser(user)
        fetchData()
    }

    fun fetchData() {
        val user = userApi.getUser()
        val now = LocalDateTime.now()
        userObservable.updateValue(DisplayableUser(user.getFullName(), user.getAge(now)))
    }

    fun getDisplayableUserObservable(): FieldObservable<DisplayableUser> {
        return userObservable
    }

    companion object {
        fun create(): UserViewModel {
            val userApi = UserApi.getInstance()
            return UserViewModel(userApi)
        }
    }

}