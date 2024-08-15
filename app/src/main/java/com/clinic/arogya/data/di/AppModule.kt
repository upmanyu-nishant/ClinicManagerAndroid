package com.clinic.arogya.data.di

import android.content.Context
import androidx.room.Room
import com.clinic.arogya.data.local.PatientDatabase
import com.clinic.arogya.data.repository.PatientRepositoryImpl
import com.clinic.arogya.domain.repository.PatientRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePatientDatabase(@ApplicationContext context: Context): PatientDatabase =
        Room.databaseBuilder(
            context,
            PatientDatabase::class.java,
            PatientDatabase.name
        ).fallbackToDestructiveMigration ()
            .build()

    @Provides
    @Singleton
    fun providePatientRepository(database: PatientDatabase): PatientRepository =
        PatientRepositoryImpl(dao = database.dao)
}