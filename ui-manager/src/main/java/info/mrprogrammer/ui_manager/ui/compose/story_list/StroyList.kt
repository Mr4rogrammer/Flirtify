package info.mrprogrammer.ui_manager.ui.compose.story_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.mrprogrammer.ui_manager.ui.compose.circular_image.CircularImageView
import info.mrprogrammer.ui_manager.ui.compose.story_list.model.StoryListDataModel


@Composable
fun StoryList(
    storyListDataModelList: List<StoryListDataModel>,
    onClick: (storyListDataModel: StoryListDataModel) -> Unit
) {
    LazyRow(modifier = Modifier.background(Color.Transparent)) {
        items(storyListDataModelList.size) { index ->
            val data = storyListDataModelList[index]
            Surface(modifier = Modifier
                .width(90.dp)
                .background(MaterialTheme.colorScheme.background)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onClick(data) }) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Box {
                        CircularImageView(
                            modifier = Modifier.width(73.dp).height(73.dp),
                            url = data.imageUrl,
                            contentDescription = "Circular Image",
                            displayBorder = !data.isCurrentUser,
                            isStory = data.isCurrentUser
                        )

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        text = data.title,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }
}
