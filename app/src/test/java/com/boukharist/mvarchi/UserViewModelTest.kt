package com.boukharist.mvarchi

import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

class UserViewModelTest {

    @Mock
    private lateinit var userApi: IUserApi

    private lateinit var viewModel: UserViewModel

    private val mockUser = User(FIRST_NAME, LAST_NAME, BIRTH_DATE)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = UserViewModel(userApi)

        //GIVEN
        whenever(userApi.setUser(anyObject())).thenAnswer { }
        whenever(userApi.getUser()).thenReturn(mockUser)
    }

    @Test
    fun test_on_validateClicked_sets_the_right_user() {
        //WHEN
        viewModel.onValidateClicked(FIRST_NAME, LAST_NAME, BIRTH_DATE)

        //THEN
        val argumentCaptor = ArgumentCaptor.forClass(User::class.java)
        verify(userApi).setUser(capture<User>(argumentCaptor))
        assertEquals(mockUser, argumentCaptor.value)
    }

    @Test
    fun test_on_validateClicked_populate_the_right_data() {
        //WHEN
        viewModel.onValidateClicked(FIRST_NAME, LAST_NAME, BIRTH_DATE)

        //THEN
        val resultDisplayableUser = viewModel.getDisplayableUserObservable().getValue()!!
        assertEquals(resultDisplayableUser.fullName, mockUser.getFullName())
        assertEquals(resultDisplayableUser.age, mockUser.getAge(LocalDateTime.now()))
    }
}