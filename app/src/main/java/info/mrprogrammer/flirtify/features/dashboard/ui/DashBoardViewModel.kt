package info.mrprogrammer.flirtify.features.dashboard.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import info.mrprogrammer.flirtify.common.navigation.NavManager
import info.mrprogrammer.ui_manager.R
import info.mrprogrammer.ui_manager.ui.compose.post_card.model.PostData
import info.mrprogrammer.ui_manager.ui.compose.post_card.model.TagData
import info.mrprogrammer.ui_manager.ui.compose.post_card.model.UserData
import info.mrprogrammer.ui_manager.ui.compose.story_list.model.StoryListDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val navManager: NavManager
) : ViewModel() {
    private val _postData = MutableStateFlow<MutableList<PostData>>(mutableListOf())
    val postData: StateFlow<MutableList<PostData>> get() = _postData

    private val _storyData = MutableStateFlow<MutableList<StoryListDataModel>>(mutableListOf())
    val storyData: StateFlow<MutableList<StoryListDataModel>> get() = _storyData

    init {
        initStoryData()
        initPost()
    }

    fun changeScreen(id: Long) {
        navManager.navigate(id)
    }

    fun initStoryData() {
        val data: MutableList<StoryListDataModel> = mutableListOf()
        for (i in 1..15) {
            data.add(
                StoryListDataModel(
                    title = "Krisha",
                    imageUrl = "https://i.pinimg.com/564x/b5/00/20/b500200c66f40c45fb35be6353279946.jpg"
                )
            )
        }

        data.add(
            0,
            StoryListDataModel(
                title = "My Story",
                imageUrl = "https://i.pinimg.com/564x/ed/1f/90/ed1f90acffc0efa120b29ff4bc0bbabb.jpg",
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
                    R.drawable.add_story
                ),
                content = "If you could live anywhere in the world, where would you pick?",
                userData = UserData(
                    userImageUrl = "https://i.pinimg.com/564x/02/76/fe/0276fe22403e84aff0708a08fcccf76f.jpg",
                    userName = "Krishna", userStatusText = "Mr.Programmer"
                ),
                reactIcon = info.mrprogrammer.flirtify.R.drawable.like
            )
            _postData.value.add(data)
        }
    }
}