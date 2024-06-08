package info.mrprogrammer.flirtify.core.data.data_soruce.sync_data_from_server

import kotlinx.coroutines.flow.Flow


interface SyncDataDataSource {
    suspend fun syncData(): Flow<Boolean>
}