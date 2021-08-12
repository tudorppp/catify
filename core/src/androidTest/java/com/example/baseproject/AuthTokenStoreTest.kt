package com.example.baseproject

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.baseproject.repository.authentication.AuthTokenPreferenceStorage
import com.example.baseproject.repository.authentication.AuthTokenStore
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class AuthTokenStoreTest : KoinTest {

    @get:Rule
    val rule = KoinTestRule.create {
        modules(
            module {
                single<Context> { ApplicationProvider.getApplicationContext() }
            }
        )
    }

    private val context by inject<Context>()
    private lateinit var authTokenStore: AuthTokenStore

    @Before
    fun setUp() {
        val sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        authTokenStore = AuthTokenPreferenceStorage(sharedPreferences)
    }

    @Test
    fun storeAuthToken() {
        // Given
        val token = "some random token"

        // When
        authTokenStore.storeAuthToken(token)

        // Then
        assertThat(authTokenStore.getAuthToken()).isEqualTo(token)
    }
}