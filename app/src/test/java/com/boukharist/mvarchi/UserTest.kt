package com.boukharist.mvarchi

import org.junit.Test

import org.junit.Assert.*
import org.junit.runners.JUnit4
import java.time.LocalDateTime

class UserTest {

    @Test
    fun test_getAge_returns_right_age() {
        val user = User("Kanye", "West", "11/07/1990")
        val now = LocalDateTime.of(2020, 8, 11, 0, 0, 0)
        val expectedResult = 30
        assertEquals(user.getAge(now), expectedResult)
    }

    @Test
    fun test_getFullName_returns_full_name() {
        val user = User("Kanye", "West", "11/07/1990")
        val expectedResult = "Kanye West"
        assertEquals(user.getFullName(), expectedResult)
    }
}