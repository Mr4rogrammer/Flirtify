package info.mrprogrammer.flirtify.core.domain.use_case

import info.mrprogrammer.flirtify.core.domain.model.UserDataModel
import info.mrprogrammer.flirtify.core.domain.repository.GetUserLoginDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserLoginDetails @Inject constructor(
    private val getUserLoginDetailsRepository: GetUserLoginDetailsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): UserDataModel {
        return withContext(dispatcher) {
            getUserLoginDetailsRepository.getUserLoginDetails()
        }
    }
}