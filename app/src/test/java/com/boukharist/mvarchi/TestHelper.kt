package com.boukharist.mvarchi

import org.mockito.Mockito

fun <T> anyObject(): T {
    Mockito.any<T>()
    return uninitialized()
}

private fun <T> uninitialized(): T = null as T


const val FIRST_NAME = "firstname"
const val LAST_NAME = "lastname"
const val BIRTH_DATE = "11/07/1990"
val mockUser = User(FIRST_NAME, LAST_NAME, BIRTH_DATE)

