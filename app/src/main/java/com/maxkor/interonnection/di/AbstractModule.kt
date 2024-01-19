package com.maxkor.interonnection.di

import com.maxkor.interonnection.data.db.MainRepositoryImpl
import com.maxkor.interonnection.domain.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AbstractModule {

    @Binds
    fun bindMainRepository(impl: MainRepositoryImpl): MainRepository
}