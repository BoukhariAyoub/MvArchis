package com.boukharist.mvarchi

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

data class User(
    private val firstName: String,
    private val lastName: String,
    private val birthDate: String
) {

    fun getAge(now: LocalDateTime): Int {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val date2: LocalDateTime = LocalDate.parse(birthDate, formatter).atStartOfDay()
        return ChronoUnit.YEARS.between(date2, now).toInt()
    }

    fun getFullName() = "$firstName $lastName"
}

interface IUserApi {
    fun setUser(user: User)
    fun getUser(): User
}

class UserApi private constructor() : IUserApi {
    private var user: User = User("Kanye", "West", "11/07/1990")

    companion object {
        private var instance: UserApi? = null

        fun getInstance(): UserApi {
            if (instance == null) {
                instance = UserApi()
            }
            return instance!!
        }
    }

    override fun setUser(user: User) {
        this.user = user
    }

    override fun getUser() = user
}
