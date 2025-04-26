package com.example.mvvmarch.homeModule.model

import com.example.mvvmarch.common.dataAccess.retrofit.WineService
import com.example.mvvmarch.common.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WineService {
    suspend fun getRedWines() = getService().getRedWines()

    private fun getService(): WineService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(WineService::class.java)
    }
}