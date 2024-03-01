package com.example.superpoderes.di

import com.example.superpoderes.data.Repository
import com.example.superpoderes.data.RepositoryImpl
import com.example.superpoderes.data.local.LocalDataSource
import com.example.superpoderes.data.local.LocalDataSourceImpl
import com.example.superpoderes.data.remote.RemoteDataSource
import com.example.superpoderes.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(defaultRepository: RepositoryImpl): Repository

    @Binds
    abstract fun bindRemoteDataSource(defaultRemoteDataSource: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}