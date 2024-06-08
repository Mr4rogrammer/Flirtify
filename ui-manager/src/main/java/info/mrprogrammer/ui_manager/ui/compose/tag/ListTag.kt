package info.mrprogrammer.ui_manager.ui.compose.tag

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.mrprogrammer.ui_manager.ui.compose.post_card.model.TagData
import info.mrprogrammer.ui_manager.ui.theme.whiteWithTransparency

@Composable
fun ListTag(
    data: List<TagData>,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    selectedIndex: Int = 0,
    onClick: ((TagData) -> Unit)? = null
) {
    var currentSelectedIndex by remember { mutableStateOf(selectedIndex) }
    LazyRow {
        items(data.size) { index ->
            val item = data[index]
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.secondary.takeIf { index == currentSelectedIndex }
                    ?: whiteWithTransparency,
                modifier = modifier
                    .padding(end = 5.dp, start = 5.dp)
                    .border(
                        width = 0.5.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable {
                        onClick?.let {
                            onClick(item)
                            currentSelectedIndex = index
                        }
                    }
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = item.tagIcon),
                        contentDescription = "",
                        modifier = Modifier.width(20.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = item.tagName,
                        color = textColor,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}