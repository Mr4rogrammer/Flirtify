package info.mrprogrammer.flirtify.core.data.repository

import info.mrprogrammer.flirtify.core.data.data_soruce.save_data.SaveDataDataSource
import info.mrprogrammer.flirtify.core.domain.repository.SaveDataRepository
import info.mrprogrammer.flirtify.core.domain.model.SaveDataModel
import javax.inject.Inject

class SaveDataRepositoryImpl @Inject constructor(private val saveDataDataSource: SaveDataDataSource): SaveDataRepository {
    override suspend fun saveData(data: SaveDataModel): Boolean {
        return saveDataDataSource.saveData(data)
    }
}