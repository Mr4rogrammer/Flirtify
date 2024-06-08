package info.mrprogrammer.flirtify.core.data.repository

import info.mrprogrammer.flirtify.core.data.data_soruce.sync_data_from_server.SyncDataDataSource
import info.mrprogrammer.flirtify.core.domain.repository.SyncDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SyncDataRepositoryImpl @Inject constructor(private val syncDataDataSource: SyncDataDataSource): SyncDataRepository {
    override suspend fun syncData(): Flow<Boolean> {
        return syncDataDataSource.syncData()
    }
}