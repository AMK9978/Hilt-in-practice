package com.hoodad.test.di

import android.content.Context
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
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Inject
    lateinit var token: String

    @Inject
    lateinit var appVersion: String

    @Inject
    lateinit var packageName: String

    @Inject
    lateinit var buildNumber: String

    @Inject
    lateinit var deviceID: String

    @Inject
    lateinit var deviceModel: String

    @Inject
    lateinit var deviceType: String

    @Inject
    lateinit var osVersion: String

    @Inject
    lateinit var accountType: String

    @Inject
    lateinit var needProfile: String

    companion object {
        private const val BASE_URL = "http://staging.api.taakmedia.ir/api/"

    }

    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor {
        val newRequest =
            it.request().newBuilder().addHeader("X-Ham-Seda-Login-Token", token)
                .addHeader("X-Ham-Seda-App_Version", appVersion)
                .addHeader("X-Ham-Seda-Package-Name", packageName)
                .addHeader("X-Ham-Seda-Build-Number", buildNumber)
                .addHeader("X-Ham-Seda-Device-Id", deviceID)
                .addHeader("X-Ham-Seda-Device-Model", deviceModel)
                .addHeader("X-Ham-Seda-Device-Type", deviceType)
                .addHeader("X-Ham-Seda-Account-Type", accountType)
                .addHeader("X-Ham-Seda-OS-Version", osVersion)
                .addHeader("X-Ham-Seda-Need-Profile", needProfile)
                .build()
        it.proceed(newRequest)
    }.build()

    @Qualifiers.BASE_URL
    @Singleton
    @Provides
    fun provideBaseURL() = BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
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