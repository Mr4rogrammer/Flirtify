package info.mrprogrammer.flirtify.core.data.data_soruce.save_data

import info.mrprogrammer.flirtify.core.domain.model.SaveDataModel

interface SaveDataDataSource {
    suspend fun saveData(data: SaveDataModel): Boolean
}