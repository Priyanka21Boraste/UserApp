package com.user.poc.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PopulationApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NationalizeApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserApi