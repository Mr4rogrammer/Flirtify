package info.mrprogrammer.ui_manager.ui.compose.story_list.model

data class StoryListDataModel(
    var userId:Long = 0L,
    var imageUrl: Any = "",
    val title: String = "",
    var isCurrentUser: Boolean = false
)