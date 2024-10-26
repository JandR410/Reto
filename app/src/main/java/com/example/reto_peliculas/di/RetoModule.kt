package com.example.reto_peliculas.di

import android.content.Context
import androidx.room.Room
import com.example.reto_peliculas.data.local.dao.MovieDao
import com.example.reto_peliculas.data.local.db.MovieDatabase
import com.example.reto_peliculas.data.remote.MovieApi
import com.example.reto_peliculas.data.repository.MovieRepositoryImpl
import com.example.reto_peliculas.domain.repository.MovieRepository
import com.example.reto_peliculas.usecase.AuthUseCase
import com.example.reto_peliculas.usecase.GetMoviesEntityUseCase
import com.example.reto_peliculas.usecase.GetMoviesUseCase
import com.example.reto_peliculas.utils.createUnsafeOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetoModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(createUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: MovieDatabase): MovieDao {
        return appDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideRetoRepository(
        movieApi: MovieApi,
        dao: MovieDao
    ): MovieRepository = MovieRepositoryImpl(movieApi, dao)

    @Provides
    @Singleton
    fun provideUserSignUpUseCase(retoRepositoryImpl: MovieRepositoryImpl): AuthUseCase =
        AuthUseCase(retoRepositoryImpl)

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(retoRepositoryImpl: MovieRepositoryImpl): GetMoviesUseCase {
        return GetMoviesUseCase(retoRepositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetMoviesEntityUseCase(retoRepositoryImpl: MovieRepositoryImpl): GetMoviesEntityUseCase {
        return GetMoviesEntityUseCase(retoRepositoryImpl)
    }
}