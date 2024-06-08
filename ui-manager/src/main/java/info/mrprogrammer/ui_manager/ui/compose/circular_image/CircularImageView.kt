package info.mrprogrammer.ui_manager.ui.compose.circular_image

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import info.mrprogrammer.ui_manager.R

@Composable
fun CircularImageView(
    modifier: Modifier = Modifier,
    url: Any,
    contentDescription: String?,
    shape: Shape = CircleShape,
    displayBorder: Boolean = true,
    isStory: Boolean = false,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .then(
                if (displayBorder) {
                    Modifier.border(2.dp, MaterialTheme.colorScheme.secondary, shape)
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Box(
                modifier = modifier
                    .clip(shape)
                    .size(65.dp)
                    .then(
                        if (displayBorder) {
                            Modifier.border(1.dp, MaterialTheme.colorScheme.background, shape)
                        } else {
                            Modifier
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = url,
                    contentScale = ContentScale.Crop,
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .size(65.dp)
                        .clip(shape)
                )
                if (isStory) {
                    Image(
                        painter = painterResource(id = R.drawable.add_story),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp).align(Alignment.BottomEnd).offset((-7).dp, (-7).dp)
                    )
                }

            }

        }
    }
}