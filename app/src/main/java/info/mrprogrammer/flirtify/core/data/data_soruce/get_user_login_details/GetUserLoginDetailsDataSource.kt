package info.mrprogrammer.flirtify.core.data.data_soruce.get_user_login_details

import info.mrprogrammer.flirtify.core.domain.model.UserDataModel

interface GetUserLoginDetailsDataSource {
    suspend fun getUserLoginDetails(): UserDataModel
}