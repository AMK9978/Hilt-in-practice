package com.hoodad.test.di

import javax.inject.Qualifier

class Qualifiers {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Token

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AppVersion

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PackageName

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BuildNumber

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DeviceID

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DeviceModel

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DeviceType

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OsVersion

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AccountType

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class NeedProfile

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BASE_URL
}