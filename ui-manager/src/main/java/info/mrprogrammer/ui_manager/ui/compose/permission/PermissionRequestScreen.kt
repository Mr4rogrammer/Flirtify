package info.mrprogrammer.ui_manager.ui.compose.permission

import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import info.mrprogrammer.ui_manager.ui.compose.lotfiles.LotifilesAnimatedPreloader
import info.mrprogrammer.ui_manager.ui.compose.permission.model.PermissionRequestModel

@Composable
fun PermissionRequestScreen(
    lottieRawId: Int,
    permissionRequestModel: List<PermissionRequestModel>,
    onPermissionGranted: () -> Unit
) {
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }
    var dialogContent by remember {
        mutableStateOf("This app needs permissions to work properly. Please grant the required permissions or allow in settings.")
    }
    var dialogButtonText by remember {
        mutableStateOf("Allow")
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val deniedPermissions = permissions.filter { !it.value }.keys
        if (deniedPermissions.isNotEmpty()) {
            deniedPermissions.forEach {
                val shouldShowRationale =
                    ActivityCompat.shouldShowRequestPermissionRationale((context as Activity), it)
                if (!shouldShowRationale) {
                    dialogContent = "Kindly grant permission in settings screen."
                    dialogButtonText = "Okey"
                }
                showDialog = true
            }
        } else {
            onPermissionGranted()
        }

    }


    if (showDialog) {
        AlertDialog(
            onDismissRequest = { /* Prevent dismissing the dialog */ },
            title = { Text("Request Permissions") },
            text = { Text(dialogContent) },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    if (dialogButtonText == "Allow") {
                        permissionLauncher.launch(permissionRequestModel.map { it.permission }
                            .toTypedArray())
                    }
                }) {
                    Text(dialogButtonText)
                }
            },
            dismissButton = {
                Button(onClick = { (context as? Activity)?.finish() }) {
                    Text("Deny")
                }
            }
        )
    }


    fun handlePermission() {
        permissionLauncher.launch(permissionRequestModel.map { it.permission }.toTypedArray())
    }

    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                LotifilesAnimatedPreloader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f), rawId = lottieRawId
                )

                Text(
                    text = "Why we need permission ?",
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(15.dp))

                LazyColumn {
                    items(permissionRequestModel.size) { index ->
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "${index + 1}) ${permissionRequestModel[index].reason}",
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        handlePermission()
                    }
                    .padding(16.dp)
                    .clip(RoundedCornerShape(30.dp))) {
                    Surface(
                        modifier = Modifier.align(Alignment.Center),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = "Grant Permissions",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.secondary)
                                .padding(12.dp)
                        )
                    }
                }

            }
        }

    }
}

fun customCheckPermission(permission: String, activity: Activity): Boolean {
    return ContextCompat.checkSelfPermission(
        activity,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}