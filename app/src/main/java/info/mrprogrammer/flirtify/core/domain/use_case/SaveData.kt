package info.mrprogrammer.flirtify.core.domain.use_case

import info.mrprogrammer.flirtify.core.domain.repository.SaveDataRepository
import info.mrprogrammer.flirtify.core.domain.model.SaveDataModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveData @Inject constructor(private val saveDataRepository: SaveDataRepository,
                                   private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    suspend operator fun invoke(data: SaveDataModel): Boolean {
        return withContext(dispatcher) {
            saveDataRepository.saveData(data)
        }
    }
}