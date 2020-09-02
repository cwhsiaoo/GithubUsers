package xlet.android.example.githubusers.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import xlet.android.example.githubusers.repository.db.AppDatabase


@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "database-name"

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()
}