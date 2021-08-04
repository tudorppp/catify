package com.example.baseproject.networking

import javax.inject.Inject

internal class StagingDataSourceImpl @Inject constructor(): StagingDataSource {

    override fun getBaseUrl() = "https://api.thecatapi.com/v1/"
}