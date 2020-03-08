package com.boukharist.mvarchi

open class UserController(private val userApi: IUserApi, private val view: IView) {

    fun onValidateClicked(firstName: String, lastName: String, birthDate: String) {
        val user = User(firstName, lastName, birthDate)
        userApi.setUser(user)
        view.populateData()
    }
}