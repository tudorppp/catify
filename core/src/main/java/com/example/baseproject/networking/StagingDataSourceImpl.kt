package com.example.baseproject.networking

internal class StagingDataSourceImpl : StagingDataSource {

    override fun getBaseUrl() = "https://api.thecatapi.com/v1/"
}