package com.feri.pokemon.di

import android.content.Context
import com.feri.pokemon.BuildConfig
import com.feri.pokemon.data.remote.PokemonApi
import com.feri.pokemon.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetwokModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        val httpCacheDirectory = File(context.cacheDir, "responses")

        try {
            val cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
            httpClient.cache(cache)
        } catch (e: IOException) {
            Timber.e(e)
        }

        if (BuildConfig.DEBUG) {
            httpClient
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return httpClient.build()
    }

    @Singleton
    @Provides
    fun providePokemonRepository(
        api: PokemonApi
    ) = PokemonRepository(api)

    @Singleton
    @Provides
    fun providePokemonApi(client: OkHttpClient): PokemonApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL_API)
            .client(client)
            .build()
            .create(PokemonApi::class.java)
    }
}