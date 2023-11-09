package net.softglobe.loginregistersampleapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api : ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(URLs.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}