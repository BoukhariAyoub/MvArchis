package com.boukharist.mvarchi.form

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.boukharist.mvarchi.*
import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.*

class FormViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userApi: IUserApi

    @InjectMocks
    private lateinit var viewModel: FormViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun test_on_validateClicked_sets_the_right_user() {
        //GIVEN
        whenever(userApi.setUser(anyObject())).thenAnswer { }
        viewModel.firstName.set(FIRST_NAME)
        viewModel.lastName.set(LAST_NAME)
        viewModel.birthDate.set(BIRTH_DATE)

        //WHEN
        viewModel.onValidateClicked()

        //THEN
        val argumentCaptor = ArgumentCaptor.forClass(User::class.java)
        Mockito.verify(userApi).setUser(capture<User>(argumentCaptor))
        assertEquals(mockUser, argumentCaptor.value)
    }
}