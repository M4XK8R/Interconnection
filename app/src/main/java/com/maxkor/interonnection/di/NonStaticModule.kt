package com.maxkor.interonnection.di

import android.content.Context
import androidx.room.Room
import com.maxkor.interonnection.data.db.InternalDataBase
import com.maxkor.interonnection.data.retrofit.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val DB_NAME = "offline_database"

//private const val BASE_URL = "https://thronesapi.com/"
private const val BASE_URL = "https://api.coinranking.com/"

@Module
@InstallIn(SingletonComponent::class)
class NonStaticModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): InternalDataBase {
        return Room.databaseBuilder(
            context,
            InternalDataBase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideApi(): ApiService {

        val interceptor = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(ApiService::class.java)
    }
}