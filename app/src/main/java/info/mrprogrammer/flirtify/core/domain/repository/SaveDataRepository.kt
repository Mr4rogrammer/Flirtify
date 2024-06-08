package info.mrprogrammer.flirtify.core.domain.repository

import info.mrprogrammer.flirtify.core.domain.model.SaveDataModel

interface SaveDataRepository {
    suspend fun saveData(data: SaveDataModel): Boolean
}