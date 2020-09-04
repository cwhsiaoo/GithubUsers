package xlet.android.example.githubusers.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xlet.android.example.githubusers.repository.api.GithubApi


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.github.com/"

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply { level = BODY }
            )
            .build()

    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(HttpUrl.parse(BASE_URL)!!)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideGithubApi(
        retrofit: Retrofit
    ): GithubApi = retrofit.create(GithubApi::class.java)
}