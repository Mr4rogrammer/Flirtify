package info.mrprogrammer.flirtify.features.add.ui.screen

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import info.mrprogrammer.flirtify.R
import info.mrprogrammer.flirtify.common.bottomNavgation.getBottomNavigationDetails
import info.mrprogrammer.flirtify.features.add.ui.viewmodel.PostOrStoryViewModel
import info.mrprogrammer.ui_manager.ui.compose.image_picker.ImagePicker
import info.mrprogrammer.ui_manager.ui.compose.navigation.BottomNavigationBar
import info.mrprogrammer.ui_manager.ui.compose.tag.ListTag

@Composable
fun AddPostScreen(postOrStoryViewModel: PostOrStoryViewModel = hiltViewModel()) {
    val (title, setTitle) = remember { mutableStateOf("New Story") }
    var content by remember { mutableStateOf("") }

    val tagList by postOrStoryViewModel.tagList.collectAsState()


    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
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
                        text = title,
                        fontSize = 28.sp,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Image(
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = "",
                        modifier = Modifier.width(48.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    ImagePicker(defaultImage = "https://i.pinimg.com/564x/2a/6b/bb/2a6bbb6053df272f1d35149211ec0e1a.jpg") {
                        postOrStoryViewModel.setImage(it)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                ListTag(
                    data = tagList,
                    textColor = MaterialTheme.colorScheme.primary,
                    selectedIndex = 0
                ) { data ->

                }
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.7f),
                    value = content,
                    maxLines = 10,
                    onValueChange = { content = it },
                    label = { Text("Enter something about today") }
                )

            }


            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                BottomNavigationBar(
                    getBottomNavigationDetails(), currentSelected = 2, modifier = Modifier
                        .padding(bottom = 20.dp)
                ) {
                    postOrStoryViewModel.changeScreen(it.id)
                }
            }
        }
    }
}