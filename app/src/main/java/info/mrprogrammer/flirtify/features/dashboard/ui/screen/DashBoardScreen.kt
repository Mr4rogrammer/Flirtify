package info.mrprogrammer.flirtify.features.dashboard.ui.screen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import info.mrprogrammer.flirtify.R
import info.mrprogrammer.flirtify.common.bottomNavgation.getBottomNavigationDetails
import info.mrprogrammer.flirtify.features.dashboard.ui.viewmodel.DashBoardViewModel
import info.mrprogrammer.ui_manager.ui.compose.navigation.BottomNavigationBar
import info.mrprogrammer.ui_manager.ui.compose.post_card.PostCard
import info.mrprogrammer.ui_manager.ui.compose.story_list.StoryList
import info.mrprogrammer.ui_manager.ui.compose.tab.TwoTab

@Composable
fun DashBoardScreen(dashBoardViewModel: DashBoardViewModel = hiltViewModel()) {
    val postData by dashBoardViewModel.postData.collectAsState()
    val storyData by dashBoardViewModel.storyData.collectAsState()

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
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge, fontSize = 28.sp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.notificationicon),
                        contentDescription = "",
                        modifier = Modifier.width(48.dp)
                    )
                }


                Spacer(modifier = Modifier.height(20.dp))
                StoryList(storyData) {

                }
                Spacer(modifier = Modifier.height(20.dp))
                TwoTab(leftTitle = "Make Friends", rightTitle = "Search Partners") {

                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn {
                    items(postData.size) {
                        PostCard(postData = postData.get(it)) {

                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }

            BottomNavigationBar(
                getBottomNavigationDetails(), currentSelected = 0, modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
            ) {
                dashBoardViewModel.changeScreen(it.id)
            }
        }
    }
}
