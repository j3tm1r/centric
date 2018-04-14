package com.jxhem.uicentric.datalayer

import android.content.Context
import android.content.SharedPreferences
import com.jxhem.uicentric.datalayer.net.DataService
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataModule {

    val PRODUCTION_API_URL: HttpUrl = HttpUrl.parse(DataService.BASE_URL)!!


    fun provideBaseUrl(): HttpUrl {
        return PRODUCTION_API_URL
    }

    fun provideOkHttpClient(): OkHttpClient {
        return createApiClient().build()
    }

    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("uicentric", Context.MODE_PRIVATE)
    }

    fun provideRetrofit(baseUrl: HttpUrl, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun provideDataService(): DataService {
        return provideRetrofit(provideBaseUrl(), provideOkHttpClient())
                .create(DataService::class.java)
    }

    private fun createApiClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }
}
