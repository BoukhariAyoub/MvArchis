package com.boukharist.mvarchi

import org.junit.Test

import org.junit.Assert.*
import org.junit.runners.JUnit4
import java.time.LocalDateTime

class UserTest {

    @Test
    fun test_getFullName_returns_full_name() {
        val user = User("Kanye", "West")
        val expectedResult = "Kanye West"
        assertEquals(user.getFullName(), expectedResult)
    }
}