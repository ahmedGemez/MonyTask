package com.example.data.di

import com.example.data.apiservice.ApiService
import com.example.data.repos.RemoteRepoImpl
import com.example.domain.repositories.RemoteRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module(includes = [NetworkModule::class])
public class ApiModule {
    @Provides
   public fun provideRemoteRepo(remoteRepoImpl:RemoteRepoImpl):RemoteRepo{
        return remoteRepoImpl;
    }

}