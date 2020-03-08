package com.boukharist.mvarchi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

class UserViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userApi: IUserApi

    private lateinit var viewModel: UserViewModel

    private val mockUser = User(FIRST_NAME, LAST_NAME, BIRTH_DATE)

    @Mock
    private lateinit var mockUserObserver: Observer<DisplayableUser>

    @Mock
    private lateinit var mockFormObserver: Observer<FormFields>


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
        viewModel.userLiveData.observeForever(mockUserObserver)

        //THEN
        val argumentCaptor = ArgumentCaptor.forClass(DisplayableUser::class.java)
        verify(mockUserObserver).onChanged(capture<DisplayableUser>(argumentCaptor))
        assertEquals(argumentCaptor.value.fullName, mockUser.getFullName())
        assertEquals(argumentCaptor.value.age, mockUser.getAge(LocalDateTime.now()))
    }

    @Test
    fun test_on_form_fields_set() {
        //WHEN
        viewModel.onFormTextChanged(FIRST_NAME, LAST_NAME, BIRTH_DATE)
        viewModel.formLiveData.observeForever(mockFormObserver)

        //THEN
        val argumentCaptor = ArgumentCaptor.forClass(FormFields::class.java)
        verify(mockFormObserver).onChanged(capture<FormFields>(argumentCaptor))
        assertEquals(argumentCaptor.value.firstName, FIRST_NAME)
        assertEquals(argumentCaptor.value.lastName, LAST_NAME)
        assertEquals(argumentCaptor.value.birthDate, BIRTH_DATE)
    }
}