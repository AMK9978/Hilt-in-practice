package com.hoodad.test.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.hoodad.test.data.api.APIHelper
import com.hoodad.test.data.api.APIHelperImpl
import com.hoodad.test.data.api.APIService
import com.hoodad.test.utils.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideClient(@ApplicationContext context: Context) =
        OkHttpClient.Builder().addInterceptor {
            val newRequest =
                it.request().newBuilder()
                    .addHeader("X-Ham-Seda-Login-Token", provideToken(context))
                    .addHeader("X-Ham-Seda-App_Version", provideAppVersion(context))
                    .addHeader("X-Ham-Seda-Package-Name", providePackageName(context))
                    .addHeader("X-Ham-Seda-Build-Number", provideBuildNumber(context))
                    .addHeader("X-Ham-Seda-Device-Id", provideDeviceID(context))
                    .addHeader("X-Ham-Seda-Device-Model", provideDeviceModel(context))
                    .addHeader("X-Ham-Seda-Device-Type", provideDeviceType(context))
                    .addHeader("X-Ham-Seda-Account-Type", provideAccountType(context))
                    .addHeader("X-Ham-Seda-OS-Version", provideOsVersion(context))
                    .addHeader("X-Ham-Seda-Need-Profile", provideNeedProfile(context))
                    .build()
            it.proceed(newRequest)
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("http://staging.api.taakmedia.ir/api/")
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            ).build()

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit) = retrofit.create(APIService::class.java)

    @Provides
    @Singleton
    fun provideAPIHelper(apiHelperImpl: APIHelperImpl)
            : APIHelper = apiHelperImpl

    @Qualifiers.Token
    @Singleton
    @Provides
    fun provideToken(@ApplicationContext applicationContext: Context): String =
        Util.getPrefString(applicationContext, Util.TOKEN)

    @Qualifiers.AppVersion
    @Singleton
    @Provides
    fun provideAppVersion(@ApplicationContext applicationContext: Context): String =
        Util.getPrefString(applicationContext, Util.APP_VERSION)

    @Qualifiers.PackageName
    @Singleton
    @Provides
    fun providePackageName(@ApplicationContext applicationContext: Context): String =
        Util.getPrefString(applicationContext, Util.PACKAGE_NAME)

    @Qualifiers.BuildNumber
    @Singleton
    @Provides
    fun provideBuildNumber(@ApplicationContext applicationContext: Context): String =
        Util.getPrefString(applicationContext, Util.BUILD_NUMBER)

    @Qualifiers.DeviceID
    @Singleton
    @Provides
    fun provideDeviceID(@ApplicationContext applicationContext: Context): String =
        Util.getPrefString(applicationContext, Util.DEVICE_ID)

    @Qualifiers.DeviceModel
    @Singleton
    @Provides
    fun provideDeviceModel(@ApplicationContext applicationContext: Context): String =
        Util.getPrefString(applicationContext, Util.DEVICE_MODEL)

    @Qualifiers.DeviceType
    @Singleton
    @Provides
    fun provideDeviceType(@ApplicationContext applicationContext: Context): String =
        Util.getPrefString(applicationContext, Util.DEVICE_TYPE)

    @Qualifiers.AccountType
    @Singleton
    @Provides
    fun provideAccountType(@ApplicationContext applicationContext: Context): String =
        Util.getPrefString(applicationContext, Util.ACCOUNT_TYPE)

    @Qualifiers.OsVersion
    @Singleton
    @Provides
    fun provideOsVersion(@ApplicationContext applicationContext: Context): String =
        Util.getPrefString(applicationContext, Util.OS_VERSION)

    @Qualifiers.NeedProfile
    @Singleton
    @Provides
    fun provideNeedProfile(@ApplicationContext applicationContext: Context): String =
        Util.getPrefString(applicationContext, Util.NEED_PROFILE)
}