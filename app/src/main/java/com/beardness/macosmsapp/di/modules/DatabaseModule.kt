package com.beardness.macosmsapp.di.modules

import android.content.Context
import androidx.room.Room
import com.beardness.macosmsapp.db.MacoDb
import com.beardness.macosmsapp.db.smscache.SmsCacheDao
import com.beardness.macosmsapp.db.translate.TranslateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMacoDatabase(
        @ApplicationContext context: Context,
    ): MacoDb =
        Room.databaseBuilder(
            context = context,
            klass = MacoDb::class.java,
            name = MacoDb.NAME,
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideTranslateDao(
        macoDb: MacoDb,
    ): TranslateDao =
        macoDb.translateDao()

    @Provides
    @Singleton
    fun provideSmsCacheDao(
        macoDb: MacoDb,
    ): SmsCacheDao =
        macoDb.smsCacheDao()

}