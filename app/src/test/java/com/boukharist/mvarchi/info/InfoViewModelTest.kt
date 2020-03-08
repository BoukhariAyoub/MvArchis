package com.boukharist.mvarchi.info

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.boukharist.mvarchi.*
import com.boukharist.mvarchi.form.FormViewModel
import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.*
import java.time.LocalDateTime

class InfoViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userApi: IUserApi

    @InjectMocks
    private lateinit var viewModel: InfoViewModel

    @Mock
    private lateinit var mockUserObserver: Observer<DisplayableUser>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test_on_Api_changed_populate_right_user() {
        //GIVEN
        val mockLiveData = MutableLiveData(mockUser)
        whenever(userApi.getUser()).thenReturn(mockLiveData)
        whenever(userApi.setUser(anyObject())).then {
            { mockLiveData.value = mockUser }
        }
        viewModel.userLiveData.observeForever(mockUserObserver)

        //WHEN
        viewModel.fetchData()

        //THEN
        val argumentCaptor = ArgumentCaptor.forClass(DisplayableUser::class.java)
        Mockito.verify(mockUserObserver).onChanged(capture<DisplayableUser>(argumentCaptor))
        Assert.assertEquals(argumentCaptor.value.fullName, mockUser.getFullName())
        Assert.assertEquals(argumentCaptor.value.age, mockUser.getAge(LocalDateTime.now()))
    }
}