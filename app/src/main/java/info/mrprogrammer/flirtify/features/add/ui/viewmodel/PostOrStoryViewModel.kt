package info.mrprogrammer.flirtify.features.add.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import info.mrprogrammer.flirtify.R
import info.mrprogrammer.flirtify.common.navigation.NavManager
import info.mrprogrammer.ui_manager.ui.compose.post_card.model.TagData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PostOrStoryViewModel @Inject constructor(private val navManager: NavManager): ViewModel() {
    private val _tagList = MutableStateFlow<MutableList<TagData>>(mutableListOf())
    val tagList = _tagList.asStateFlow()

    private var _image: String? = null


    init {
        initTagData()
    }

    fun changeScreen(id: Long) {
        navManager.navigate(id)
    }

    fun setImage(it: String?) {
        _image = it
    }

    private fun initTagData() {
        val dataList = mutableListOf<TagData>()

        for ( i in 1..10) {
            dataList.add(TagData(tagIcon = R.drawable.tree, tagName = "Nature $i"))
        }

        _tagList.value = dataList
    }
}