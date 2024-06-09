package info.mrprogrammer.ui_manager.ui.compose.instagram

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import info.mrprogrammer.ui_manager.ui.compose.instagram.model.PostOption
import info.mrprogrammer.ui_manager.ui.theme.lightGrey

@Composable
fun PostOption(postOption: List<PostOption>, onClickListener: (PostOption) -> Unit) {
    val (selectedIndex, setSelectedIndex) = remember { mutableStateOf(0) }

    fun onClick(data: PostOption) {
        onClickListener(data)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.secondary
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(0.4f),
                horizontalArrangement = Arrangement.Center
            ) {
                items(postOption.size) { index ->
                    val data = postOption[index]
                    Surface(color = Color.Transparent, modifier = Modifier.padding(5.dp).clickable {
                        onClick(data)
                        setSelectedIndex(index)
                    }) {
                        Text(
                            color = Color.White.takeIf { selectedIndex == index } ?: MaterialTheme.colorScheme.primary,
                            text = data.name,
                            modifier = Modifier.padding(7.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}