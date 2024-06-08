package info.mrprogrammer.flirtify.core.domain.use_case

import info.mrprogrammer.flirtify.core.domain.repository.SyncDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SyncData @Inject constructor(private val syncDataRepository: SyncDataRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
                                  ) {
    suspend operator fun invoke():Flow<Boolean> {
        return syncDataRepository.syncData()
    }
}