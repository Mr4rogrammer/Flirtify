package info.mrprogrammer.flirtify.core.data.repository

import info.mrprogrammer.flirtify.core.data.data_soruce.get_user_login_details.GetUserLoginDetailsDataSource
import info.mrprogrammer.flirtify.core.domain.model.UserDataModel
import info.mrprogrammer.flirtify.core.domain.repository.GetUserLoginDetailsRepository
import javax.inject.Inject

class GetUserLoginDetailsRepositoryImpl @Inject constructor(private val getUserLoginDetailsDataSource: GetUserLoginDetailsDataSource) :
    GetUserLoginDetailsRepository {
    override suspend fun getUserLoginDetails(): UserDataModel {
        return getUserLoginDetailsDataSource.getUserLoginDetails()
    }
}