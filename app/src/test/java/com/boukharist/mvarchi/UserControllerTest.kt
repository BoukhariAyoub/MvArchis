package com.boukharist.mvarchi

import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.verify

class UserControllerTest {

    @Spy
    lateinit var userApi: IUserApi

    @Mock
    lateinit var view: IView

    lateinit var controller: UserController


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        //  MockKAnnotations.init(this)
        controller = UserController(userApi, view)

        //GIVEN
        whenever(userApi.setUser(anyObject())).thenAnswer { }
        whenever(view.populateData()).thenAnswer { }
    }

    @Test
    fun test_on_validateClicked_calls_api() {
        //WHEN
        val user = User(FIRST_NAME, LAST_NAME, BIRTH_DATE)
        controller.onValidateClicked(FIRST_NAME, LAST_NAME, BIRTH_DATE)

        //THEN
        val argumentCaptor = ArgumentCaptor.forClass(User::class.java)
        verify(userApi).setUser(capture<User>(argumentCaptor))
        Assert.assertEquals(user, argumentCaptor.value)
    }

    @Test
    fun test_on_validateClicked_notify_view() {
        //WHEN
        controller.onValidateClicked(FIRST_NAME, LAST_NAME, BIRTH_DATE)

        //THEN
        verify(view).populateData()
    }
}