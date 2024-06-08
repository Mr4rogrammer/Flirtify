package info.mrprogrammer.ui_manager.ui.compose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import info.mrprogrammer.ui_manager.ui.compose.circular_image.CircularImageView
import info.mrprogrammer.ui_manager.ui.compose.navigation.model.BottomNavigationBarDataModel

@Composable
fun BottomNavigationBar(
    bottomNavigationBarDataModel: List<BottomNavigationBarDataModel>,
    modifier: Modifier,
    currentSelected: Long,
    onClick: (BottomNavigationBarDataModel) -> Unit
) {
    fun handleOnClick(event: BottomNavigationBarDataModel) {
        if (currentSelected !=  event.id) {
            onClick(event)
        }
    }
    Surface(modifier = modifier
        .fillMaxWidth()
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {

        }, color = Color.White, shape = RoundedCornerShape(30.dp), shadowElevation = 8.dp
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.background(Color.White).padding(5.dp)
        ) {
            bottomNavigationBarDataModel.forEach { bottomNavigationBarDataModel ->
                val currentData = bottomNavigationBarDataModel
                val icon =
                    currentData.activeIcon.takeIf { currentSelected == bottomNavigationBarDataModel.id }
                        ?: currentData.inActiveIcon
                CircularImageView(
                    url = icon,
                    contentDescription = "",
                    displayBorder = false,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clickable {
                            handleOnClick(bottomNavigationBarDataModel)
                        })
            }
        }
    }
}