package com.maxkor.interonnection.di

import com.maxkor.interonnection.data.repository.MainRepositoryImpl
import com.maxkor.interonnection.domain.helpers.ActivityResultHelper
import com.maxkor.interonnection.domain.helpers.AlarmHelper
import com.maxkor.interonnection.domain.helpers.ImageShareHelper
import com.maxkor.interonnection.domain.helpers.InternetChecker
import com.maxkor.interonnection.domain.helpers.NotificationHelper
import com.maxkor.interonnection.domain.helpers.PicturesSaver
import com.maxkor.interonnection.domain.repository.MainRepository
import com.maxkor.interonnection.helpers.ActivityResultHelperImpl
import com.maxkor.interonnection.helpers.AlarmHelperImpl
import com.maxkor.interonnection.helpers.ImageShareHelperImpl
import com.maxkor.interonnection.helpers.InternetCheckerImpl
import com.maxkor.interonnection.helpers.NotificationHelperImpl
import com.maxkor.interonnection.helpers.PicturesSaverImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AbstractModule {

    @Binds
    @Singleton
    fun bindMainRepository(impl: MainRepositoryImpl): MainRepository

    @Binds
    fun bindAlarmHelper(impl: AlarmHelperImpl): AlarmHelper

    @Binds
    fun bindNotificationHelper(impl: NotificationHelperImpl): NotificationHelper

    @Binds
    fun bindActivityResulHelper(impl: ActivityResultHelperImpl): ActivityResultHelper

    @Binds
    fun bindImageShareHelper(impl: ImageShareHelperImpl): ImageShareHelper

    @Binds
    fun bindPictureSaver(impl: PicturesSaverImpl): PicturesSaver

    @Binds
    fun bindInternetChecker(impl: InternetCheckerImpl): InternetChecker
}