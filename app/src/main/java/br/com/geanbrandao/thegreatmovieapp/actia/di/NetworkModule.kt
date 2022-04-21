package br.com.geanbrandao.thegreatmovieapp.actia.di

import br.com.geanbrandao.thegreatmovieapp.actia.data.remote.MoviesApiService
import br.com.geanbrandao.thegreatmovieapp.actia.utils.Constants.BASE_URL_MOVIES
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitMovies(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_MOVIES)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder().apply {
            addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }.build()

    @Provides
    @Singleton
    fun provideApiMovies(retrofit: Retrofit): MoviesApiService =
        retrofit.create(MoviesApiService::class.java)
}