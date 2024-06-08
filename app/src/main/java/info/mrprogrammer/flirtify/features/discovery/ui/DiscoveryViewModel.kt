package info.mrprogrammer.flirtify.features.discovery.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import info.mrprogrammer.flirtify.R
import info.mrprogrammer.flirtify.common.navigation.NavManager
import info.mrprogrammer.flirtify.core.utils.getRandomGirlName
import info.mrprogrammer.flirtify.core.utils.getRandomUrlGirlImage
import info.mrprogrammer.ui_manager.ui.compose.discover_list.model.DiscoverListDataModel
import info.mrprogrammer.ui_manager.ui.compose.post_card.model.TagData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class DiscoveryViewModel @Inject constructor(
    private val navManager: NavManager
) : ViewModel() {

    private val _discoverData =
        MutableStateFlow<MutableList<DiscoverListDataModel>>(mutableListOf())
    val discoverData = _discoverData.asStateFlow()

    private val _tagList = MutableStateFlow<MutableList<TagData>>(mutableListOf())
    val tagList = _tagList.asStateFlow()

    init {
        initDiscoverData()
        initTagData()
    }

    fun changeScreen(id: Long) {
        navManager.navigate(id)
    }

    private fun initDiscoverData() {
        val dataList = mutableListOf<DiscoverListDataModel>()
        for (i in 1..10) {
            val data = DiscoverListDataModel(
                userId = 1L,
                imageUrl = getRandomUrlGirlImage(),
                distance = "1${i.toString()} km away",
                name = getRandomGirlName(),
                age = "23",
                statusText = "Love ❤\uFE0F"
            )
            dataList.add(data)
        }
        _discoverData.value = dataList
    }

    private fun initTagData() {
        val dataList = mutableListOf<TagData>()

        for ( i in 1..10) {
            dataList.add(TagData(tagIcon = R.drawable.tree, tagName = "Nature $i"))
        }

        _tagList.value = dataList
    }
}