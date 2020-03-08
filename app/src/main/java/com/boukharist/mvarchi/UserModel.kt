package com.boukharist.mvarchi

data class User(
    private val firstName: String,
    private val lastName: String
) {
    fun getFullName() = "$firstName $lastName"
}

class UserApi {
    private var user: User = User("Kanye", "West")

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser() = user
}