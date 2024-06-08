package info.mrprogrammer.flirtify.core.domain.repository

import info.mrprogrammer.flirtify.core.domain.model.UserDataModel

interface GetUserLoginDetailsRepository {
    suspend fun getUserLoginDetails(): UserDataModel
}

