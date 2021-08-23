package cc.hubble.clipmatch.di

import cc.hubble.clipmatch.data.repository.RandomUsersRepositoryImp
import cc.hubble.clipmatch.data.repository.RandomUsersRepository
import cc.hubble.network.RandomUserApiClient
import cc.hubble.network.service.RandomUserService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Singleton
    @Binds
    abstract fun bindRandomUsersRepository(randomUsersRepositoryImp: RandomUsersRepositoryImp): RandomUsersRepository

}

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideRandomUserService(): RandomUserService = RandomUserApiClient.randomUserService
}