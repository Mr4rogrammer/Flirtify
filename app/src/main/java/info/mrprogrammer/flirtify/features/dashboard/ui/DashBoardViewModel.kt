package info.mrprogrammer.flirtify.features.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.mrprogrammer.flirtify.R
import info.mrprogrammer.flirtify.common.navigation.NavManager
import info.mrprogrammer.flirtify.core.domain.use_case.GetUserLoginDetails
import info.mrprogrammer.flirtify.core.utils.getRandomGirlName
import info.mrprogrammer.flirtify.core.utils.getRandomUrlGirlImage
import info.mrprogrammer.ui_manager.ui.compose.post_card.model.PostData
import info.mrprogrammer.ui_manager.ui.compose.post_card.model.TagData
import info.mrprogrammer.ui_manager.ui.compose.post_card.model.UserData
import info.mrprogrammer.ui_manager.ui.compose.story_list.model.StoryListDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val navManager: NavManager,
    private val getUserLoginDetails: GetUserLoginDetails
) : ViewModel() {
    private val _postData = MutableStateFlow<MutableList<PostData>>(mutableListOf())
    val postData: StateFlow<MutableList<PostData>> get() = _postData

    private val _storyData = MutableStateFlow<MutableList<StoryListDataModel>>(mutableListOf())
    val storyData: StateFlow<MutableList<StoryListDataModel>> get() = _storyData

    init {
        viewModelScope.launch {
            initStoryData()
        }
        initPost()
    }

    fun changeScreen(id: Long) {
        navManager.navigate(id)
    }

    private suspend fun initStoryData() {
        val data: MutableList<StoryListDataModel> = mutableListOf()
        for (i in 1..15) {
            data.add(
                StoryListDataModel(
                    title = getRandomGirlName(),
                    imageUrl = getRandomUrlGirlImage()
                )
            )
        }

        data.add(
            0,
            StoryListDataModel(
                title = "My Story",
                imageUrl = getUserLoginDetails().imageUrl ?: "",
                isCurrentUser = true
            )
        )
        _storyData.value = data
    }


    private fun initPost() {
        for (i in 1..15) {
            val data = PostData(
                postImageUrl = "https://i.pinimg.com/736x/c2/6d/91/c26d912eb43a85ca51ecb81804f36dc4.jpg",
                tagData = TagData(
                    "Nature",
                    R.drawable.tree
                ),
                content = "If you could live anywhere in the world, where would you pick?",
                userData = UserData(
                    userImageUrl = "https://instagram.fmaa2-4.fna.fbcdn.net/v/t51.2885-19/439900491_657834589820368_6188193405571672090_n.jpg?stp=dst-jpg_s150x150&_nc_ht=instagram.fmaa2-4.fna.fbcdn.net&_nc_cat=104&_nc_ohc=nNB02i6VgX8Q7kNvgGARxmS&edm=AEhyXUkBAAAA&ccb=7-5&oh=00_AYAnuEMpYDvDzsn2JzYCr3wUtuee03-WMvODjeRfqyulfg&oe=666A2974&_nc_sid=cf751b",
                    userName = "Krishna", userStatusText = "Mr.Programmer"
                ),
                reactIcon = info.mrprogrammer.flirtify.R.drawable.like
            )
            _postData.value.add(data)
        }
    }
}