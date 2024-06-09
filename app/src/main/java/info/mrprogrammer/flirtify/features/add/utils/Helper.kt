package info.mrprogrammer.flirtify.features.add.utils

import info.mrprogrammer.ui_manager.ui.compose.instagram.model.PostOption

fun getPostOption(): List<PostOption> {
    return listOf(
        PostOption(0, "Story"),
        PostOption(1, "Post")
    )
}