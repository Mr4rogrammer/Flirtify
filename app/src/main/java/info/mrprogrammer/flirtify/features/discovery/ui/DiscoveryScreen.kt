package info.mrprogrammer.flirtify.features.discovery.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import info.mrprogrammer.flirtify.R
import info.mrprogrammer.flirtify.common.bottomNavgation.getBottomNavigationDetails
import info.mrprogrammer.ui_manager.ui.compose.discover_list.DiscoverListRow
import info.mrprogrammer.ui_manager.ui.compose.navigation.BottomNavigationBar
import info.mrprogrammer.ui_manager.ui.compose.tag.ListTag
import info.mrprogrammer.ui_manager.ui.compose.text.textWithColoredWord
import info.mrprogrammer.ui_manager.ui.theme.grey

@Composable
fun DiscoveryScreen(discoveryViewModel: DiscoveryViewModel = hiltViewModel()) {
    val discoveryData by discoveryViewModel.discoverData.collectAsState()
    val tagList by discoveryViewModel.tagList.collectAsState()

    var selectedTag by remember { mutableStateOf("Music") }

    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.location),
                                contentDescription = "",
                                modifier = Modifier.width(15.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Sathyamangalam",
                                fontSize = 15.sp,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Image(
                                painter = painterResource(id = R.drawable.down),
                                contentDescription = "",
                                modifier = Modifier.width(15.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Discover",
                            fontSize = 28.sp,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "",
                            modifier = Modifier.width(48.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.settings),
                            contentDescription = "",
                            modifier = Modifier.width(48.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))
                DiscoverListRow(discoverListDataModelList = discoveryData) {

                }
                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Interest",
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "View all",
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                ListTag(
                    data = tagList,
                    textColor = MaterialTheme.colorScheme.primary,
                    selectedIndex = 0
                ) { data ->
                    selectedTag = data.tagName
                }
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Around Me",
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = textWithColoredWord(
                        text = "People with '${selectedTag}' interest around you",
                        highlightedWord = "'${selectedTag}'",
                        highlightColor = MaterialTheme.colorScheme.secondary
                    ),
                    color = grey,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    maxLines = 1,
                    letterSpacing = TextUnit(2f, TextUnitType.Sp)
                )
                Image(
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                )
            }

            BottomNavigationBar(
                getBottomNavigationDetails(), currentSelected = 1, modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
            ) {
                discoveryViewModel.changeScreen(it.id)
            }
        }
    }
}
