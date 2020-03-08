package com.boukharist.mvarchi

import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
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

class UserPresenterTest {

    @Mock
    private lateinit var userApi: IUserApi

    @Mock
    private lateinit var view: IView

    private lateinit var presenter: UserPresenter

    private val mockUser = User(FIRST_NAME, LAST_NAME, BIRTH_DATE)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        //  MockKAnnotations.init(this)
        presenter = UserPresenter(userApi, view)

        //GIVEN
        whenever(userApi.setUser(anyObject())).thenAnswer { }
        whenever(userApi.getUser()).thenReturn(mockUser)
        whenever(view.populateData(anyString(), anyInt())).thenAnswer { }
    }

    @Test
    fun test_on_validateClicked_sets_the_right_user() {
        //WHEN
        presenter.onValidateClicked(FIRST_NAME, LAST_NAME, BIRTH_DATE)

        //THEN
        val argumentCaptor = ArgumentCaptor.forClass(User::class.java)
        verify(userApi).setUser(capture<User>(argumentCaptor))
        assertEquals(mockUser, argumentCaptor.value)
    }

    @Test
    fun test_on_validateClicked_populate_the_right_data() {
        //WHEN
        presenter.onValidateClicked(FIRST_NAME, LAST_NAME, BIRTH_DATE)

        //THEN
        val fullNameArgCaptor = ArgumentCaptor.forClass(String::class.java)
        val ageArgCaptor = ArgumentCaptor.forClass(Int::class.java)
        verify(view).populateData(capture<String>(fullNameArgCaptor), capture<Int>(ageArgCaptor))
        assertEquals(fullNameArgCaptor.value, mockUser.getFullName())
        assertEquals(ageArgCaptor.value, mockUser.getAge(LocalDateTime.now()))
    }
}