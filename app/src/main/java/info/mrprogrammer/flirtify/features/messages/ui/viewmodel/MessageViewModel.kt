package info.mrprogrammer.flirtify.features.messages.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import info.mrprogrammer.flirtify.common.navigation.NavManager
import info.mrprogrammer.flirtify.core.utils.getRandomGirlName
import info.mrprogrammer.flirtify.core.utils.getRandomUrlGirlImage
import info.mrprogrammer.ui_manager.ui.compose.chat.model.ChatModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val navManager: NavManager
) : ViewModel() {

    private val _chatMessage = MutableStateFlow<List<ChatModel>>(emptyList())
    val chatMessage = _chatMessage.asStateFlow()

    init {
        getMessageData()
    }

    private fun getMessageData() {
        val data:MutableList<ChatModel> = mutableListOf()
        for (i in 1..40) {
            val message = ChatModel(
                messageHint = "Heyy Babe, How are you",
                imageUrl = getRandomUrlGirlImage(),
                name = getRandomGirlName(),
                time = "12:0$i"
            )
            data.add(message)
        }
        _chatMessage.value = data
    }

    fun changeScreen(id: Long) {
        navManager.navigate(id)
    }
}