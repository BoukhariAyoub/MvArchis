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

    @Mock
    private lateinit var mockFormObserver: Observer<FormFields>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun test_on_validateClicked_sets_the_right_user() {
        //GIVEN
        whenever(userApi.setUser(anyObject())).thenAnswer { }

        //WHEN
        viewModel.onValidateClicked(FIRST_NAME, LAST_NAME, BIRTH_DATE)

        //THEN
        val argumentCaptor = ArgumentCaptor.forClass(User::class.java)
        Mockito.verify(userApi).setUser(capture<User>(argumentCaptor))
        Assert.assertEquals(mockUser, argumentCaptor.value)
    }

    @Test
    fun test_on_form_fields_set() {
        //GIVEN
        whenever(userApi.setUser(anyObject())).thenAnswer { }

        //WHEN
        viewModel.onFormTextChanged(FIRST_NAME, LAST_NAME, BIRTH_DATE)
        viewModel.formLiveData.observeForever(mockFormObserver)

        //THEN
        val argumentCaptor = ArgumentCaptor.forClass(FormFields::class.java)
        Mockito.verify(mockFormObserver).onChanged(capture<FormFields>(argumentCaptor))
        assertEquals(argumentCaptor.value.firstName, FIRST_NAME)
        assertEquals(argumentCaptor.value.lastName, LAST_NAME)
        assertEquals(argumentCaptor.value.birthDate, BIRTH_DATE)
    }
}