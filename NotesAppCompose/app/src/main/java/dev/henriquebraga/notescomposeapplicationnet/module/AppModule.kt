package dev.henriquebraga.notescomposeapplicationnet.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.henriquebraga.notescomposeapplicationnet.network.ApiManager
import dev.henriquebraga.notescomposeapplicationnet.network.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNetworkInterface(): ApiService = ApiManager.retrofitService
}