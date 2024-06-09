package info.mrprogrammer.ui_manager.ui.compose.image_picker

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import info.mrprogrammer.ui_manager.ui.compose.permission.utils.isAllPermissionGranter

@Composable
fun ImagePicker(defaultImage: String, selectedUrl: (String?) -> Unit) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<String?>(defaultImage) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            imageUri = it.toString()
            selectedUrl(imageUri)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { /* Prevent dismissing the dialog */ },
            title = { Text("Request Permissions") },
            text = { Text("Kindly grant permission to access images.") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                }) {
                    Text("Okey")
                }
            },
        )
    }

    fun handleOnClick() {
        if (isAllPermissionGranter(context)) {
            launcher.launch("image/*")
            return
        } else {
            showDialog = true
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Surface(shape = RoundedCornerShape(15.dp), modifier = Modifier.clickable {
            handleOnClick()
        }) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(model = imageUri),
                contentDescription = ""
            )
        }
    }
}