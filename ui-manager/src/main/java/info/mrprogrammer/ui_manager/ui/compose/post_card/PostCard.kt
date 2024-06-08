package info.mrprogrammer.ui_manager.ui.compose.post_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import info.mrprogrammer.ui_manager.ui.compose.circular_image.CircularImageView
import info.mrprogrammer.ui_manager.ui.compose.post_card.model.PostData
import info.mrprogrammer.ui_manager.ui.compose.tag.Tag
import java.util.Locale

@Composable
fun PostCard(postData: PostData, onClick: (PostData) -> Unit) {

    Surface(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(20.dp)
    ) {
        Box {
            AsyncImage(
                model = postData.postImageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "contentDescription",
                modifier = Modifier
                    .fillMaxSize()
            )
            Tag(
                icon = postData.tagData.tagIcon,
                title = postData.tagData.tagName,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(15.dp, 15.dp)
            )

            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    text = postData.content,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 24.sp,
                    lineHeight = TextUnit(27f, TextUnitType.Sp)
                )


                Spacer(modifier = Modifier.height(30.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircularImageView(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp),
                        url = postData.userData.userImageUrl,
                        contentDescription = "Circular Image",
                        displayBorder = false
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Column(verticalArrangement = Arrangement.Center) {
                        Text(
                            modifier = Modifier.fillMaxWidth(0.7f),
                            text = postData.userData.userName,
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 14.sp,
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            modifier = Modifier.fillMaxWidth(0.7f),
                            text = postData.userData.userStatusText.toUpperCase(Locale.ROOT),
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 10.sp,
                            maxLines = 1,
                            letterSpacing = TextUnit(2f, TextUnitType.Sp)
                        )
                    }
                }
            }

            CircularImageView(
                modifier = Modifier
                    .align(Alignment.BottomEnd).padding(5.dp).width(60.dp)
                    .height(60.dp),
                url = postData.reactIcon,
                contentDescription = "Circular Image",
                displayBorder = false
            )
        }

    }
}