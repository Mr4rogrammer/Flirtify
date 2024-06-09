package info.mrprogrammer.ui_manager.ui.compose.permission.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import info.mrprogrammer.ui_manager.ui.compose.permission.customCheckPermission
import info.mrprogrammer.ui_manager.ui.compose.permission.model.PermissionRequestModel

fun getPermissionList(): List<PermissionRequestModel> {
    val permissionsToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        listOfNotNull(
            PermissionRequestModel(
                Manifest.permission.READ_MEDIA_IMAGES,
                "Needed to take pictures"
            ),
            PermissionRequestModel(
                Manifest.permission.POST_NOTIFICATIONS,
                "Needed to send notifications"
            )
        )
    } else {
        listOf(
            PermissionRequestModel(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                "Needed to read images and files"
            )
        )
    }
    return permissionsToRequest
}

fun isAllPermissionGranter(context: Context): Boolean {
    val permissionList =
        getPermissionList().filter { !customCheckPermission(it.permission, context as Activity) }
    return permissionList.isEmpty()
}