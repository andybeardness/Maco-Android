package com.beardness.macosmsapp.di.qualifiers

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoCoroutineScope

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiCoroutineScope