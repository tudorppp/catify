package com.example.core.auth

import com.example.baseproject.repository.authentication.AuthManagerImpl
import com.example.baseproject.repository.authentication.AuthTokenStore
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AuthManagerTest {

    @Test
    fun `token unavailable`() {
        // Given
        val authTokenStore = mockk<AuthTokenStore>()
        every { authTokenStore.getAuthToken() } returns null

        // When
        val authManager = AuthManagerImpl(authTokenStore)

        // Then
        assertThat(authManager.getLoginStatus()).isEqualTo(false)
    }

    @Test
    fun `token available`() {
        // Given
        val authTokenStore = mockk<AuthTokenStore>()
        every { authTokenStore.getAuthToken() } returns "some random token"

        // When
        val authManager = AuthManagerImpl(authTokenStore)

        // Then
        assertThat(authManager.getLoginStatus()).isEqualTo(true)
    }
}