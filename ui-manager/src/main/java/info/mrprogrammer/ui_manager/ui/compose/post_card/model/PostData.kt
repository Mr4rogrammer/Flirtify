package info.mrprogrammer.ui_manager.ui.compose.post_card.model

data class PostData(
    val userData: UserData = UserData(),
    val content: String = "",
    val tagData: TagData = TagData(),
    val postImageUrl: String = "",
    val reactIcon:Int = 0,
)

data class TagData(
    val tagName: String = "",
    val tagIcon: Int = 0,
)

data class UserData(
    val userId: Long = 0,
    val userEmail: String = "",
    val userName: String = "",
    val userStatusText: String = "",
    val userImageUrl: Any = "",
)
