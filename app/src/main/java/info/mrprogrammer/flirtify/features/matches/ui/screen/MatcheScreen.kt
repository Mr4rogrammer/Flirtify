package info.mrprogrammer.flirtify.features.matches.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import info.mrprogrammer.flirtify.R
import info.mrprogrammer.flirtify.common.bottomNavgation.getBottomNavigationDetails
import info.mrprogrammer.flirtify.features.discovery.ui.viewmodel.DiscoveryViewModel
import info.mrprogrammer.ui_manager.ui.compose.discover_list.DiscoverListColumn
import info.mrprogrammer.ui_manager.ui.compose.navigation.BottomNavigationBar
import info.mrprogrammer.ui_manager.ui.compose.text.textWithColoredWord

@Composable
fun MatcheScreen(discoveryViewModel: DiscoveryViewModel = hiltViewModel()) {
    val discoveryData by discoveryViewModel.discoverData.collectAsState()
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
                    Text(
                        text = "Matches",
                        fontSize = 28.sp,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Image(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = "",
                        modifier = Modifier.width(48.dp)
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = textWithColoredWord(
                        text = "Your Matches ${discoveryData.size}",
                        highlightedWord = "${discoveryData.size}",
                        highlightColor = MaterialTheme.colorScheme.secondary
                    ),
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(25.dp))
                DiscoverListColumn(discoverListDataModelList = discoveryData) {

                }
            }
            BottomNavigationBar(
                getBottomNavigationDetails(), currentSelected = 3, modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
            ) {
                discoveryViewModel.changeScreen(it.id)
            }
        }
    }
}