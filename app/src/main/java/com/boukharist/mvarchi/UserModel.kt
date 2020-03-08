package com.boukharist.mvarchi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

data class User(
    val firstName: String,
    val lastName: String,
    val birthDate: String
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
    fun getUser(): LiveData<User>
}

class UserApi private constructor() : IUserApi {
    private var user: MutableLiveData<User> = MutableLiveData(User("Kanye", "West", "11/07/1990"))

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
        this.user.value = user
    }

    override fun getUser() = user
}
