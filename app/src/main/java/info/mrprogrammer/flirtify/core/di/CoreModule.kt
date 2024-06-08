package info.mrprogrammer.flirtify.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.mrprogrammer.flirtify.core.data.data_soruce.get_user_login_details.GetUserLoginDetailsDataSource
import info.mrprogrammer.flirtify.core.data.data_soruce.get_user_login_details.local.GetUserLoginDetailsLocalDataSource
import info.mrprogrammer.flirtify.core.data.data_soruce.save_data.SaveDataDataSource
import info.mrprogrammer.flirtify.core.data.data_soruce.save_data.remote.SaveDataRemoteDataSource
import info.mrprogrammer.flirtify.core.data.data_soruce.sync_data_from_server.SyncDataDataSource
import info.mrprogrammer.flirtify.core.data.data_soruce.sync_data_from_server.remote.SyncDataRemoteDataSource
import info.mrprogrammer.flirtify.core.data.repository.GetUserLoginDetailsRepositoryImpl
import info.mrprogrammer.flirtify.core.data.repository.SaveDataRepositoryImpl
import info.mrprogrammer.flirtify.core.data.repository.SyncDataRepositoryImpl
import info.mrprogrammer.flirtify.core.domain.repository.GetUserLoginDetailsRepository
import info.mrprogrammer.flirtify.core.domain.repository.SaveDataRepository
import info.mrprogrammer.flirtify.core.domain.repository.SyncDataRepository
import info.mrprogrammer.flirtify.core.domain.use_case.GetUserLoginDetails
import info.mrprogrammer.flirtify.core.domain.use_case.SaveData
import info.mrprogrammer.flirtify.core.domain.use_case.SyncData

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun getSavaDataDataSource(): SaveDataDataSource {
        return SaveDataRemoteDataSource()
    }

    @Provides
    fun getSaveDataRepository(saveDataDataSource: SaveDataDataSource): SaveDataRepository {
        return SaveDataRepositoryImpl(saveDataDataSource)
    }

    @Provides
    fun getSaveData(saveRepository: SaveDataRepository): SaveData {
        return SaveData(saveRepository)
    }


    @Provides
    fun getUserLoginDetailsDataSource(): GetUserLoginDetailsDataSource {
        return GetUserLoginDetailsLocalDataSource()
    }

    @Provides
    fun getUserLoginDetailsRepository(getUserLoginDetailsDataSource: GetUserLoginDetailsDataSource): GetUserLoginDetailsRepository {
        return GetUserLoginDetailsRepositoryImpl(getUserLoginDetailsDataSource)
    }

    @Provides
    fun getUserLoginDetails(getUserLoginDetailsRepository: GetUserLoginDetailsRepository): GetUserLoginDetails {
        return GetUserLoginDetails(getUserLoginDetailsRepository)
    }


    @Provides
    fun getSyncDataDataSource(): SyncDataDataSource {
        return SyncDataRemoteDataSource()
    }

    @Provides
    fun getSyncDataRepository(syncDataDataSource: SyncDataDataSource): SyncDataRepository {
        return SyncDataRepositoryImpl(syncDataDataSource)
    }

    @Provides
    fun syncData(syncDataRepository: SyncDataRepository): SyncData {
        return SyncData(syncDataRepository)
    }
}