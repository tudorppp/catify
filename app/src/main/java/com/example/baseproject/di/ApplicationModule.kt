package com.example.baseproject.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val ENCRYPTED_AUTH_PREFS = "ENCRYPTED_AUTH_PREFS"

@Module
object ApplicationModule {

    @Singleton
    @Provides
    internal fun provideEncryptedSharedPreferences(context: Context): SharedPreferences {
        return EncryptedSharedPreferences.create(
            ENCRYPTED_AUTH_PREFS,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}