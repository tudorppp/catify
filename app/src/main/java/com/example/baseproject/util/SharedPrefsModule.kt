package com.example.baseproject.util

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import org.koin.dsl.module

private const val ENCRYPTED_AUTH_PREFS = "ENCRYPTED_AUTH_PREFS"

internal val sharedPrefsModule = module {

    single {
        EncryptedSharedPreferences.create(
            ENCRYPTED_AUTH_PREFS,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            get(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

}