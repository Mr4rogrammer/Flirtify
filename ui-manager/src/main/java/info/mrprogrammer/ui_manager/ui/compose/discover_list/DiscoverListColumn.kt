package info.mrprogrammer.ui_manager.ui.compose.discover_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import info.mrprogrammer.ui_manager.ui.compose.discover_list.model.DiscoverListDataModel
import info.mrprogrammer.ui_manager.ui.compose.tag.Tag
import java.util.Locale


@Composable
fun DiscoverListColumn(
    discoverListDataModelList: List<DiscoverListDataModel>,
    onClick: (discoverListDataModel: DiscoverListDataModel) -> Unit
) {
    LazyColumn(modifier = Modifier.background(Color.Transparent).fillMaxWidth().fillMaxHeight()) {
        items(discoverListDataModelList.size) { index ->
            val data = discoverListDataModelList[index]
            Box(modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight()
            ) {
                Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) {
                    AsyncImage(
                        model = data.imageUrl,
                        contentScale = ContentScale.Crop,
                        contentDescription = "contentDescription",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Tag(title = data.distance)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "${data.name}, ${data.age}",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            fontSize = 15.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = data.statusText.toUpperCase(Locale.ROOT),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 10.sp,
                            maxLines = 1,
                            letterSpacing = TextUnit(2f, TextUnitType.Sp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
