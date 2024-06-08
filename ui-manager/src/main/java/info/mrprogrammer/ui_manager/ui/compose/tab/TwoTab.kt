package info.mrprogrammer.ui_manager.ui.compose.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import info.mrprogrammer.ui_manager.ui.compose.tab.model.TabClickEvent
import info.mrprogrammer.ui_manager.ui.theme.buttonColor

@Composable
fun TwoTab(leftTitle: String, rightTitle: String, onClick: (TabClickEvent) -> Unit) {
    val (leftColor, setLeftColor) = remember { mutableStateOf(Color.White) }
    val (rightColor, setRightColor) = remember { mutableStateOf(Color.Transparent) }

    fun handleOnClick(event: TabClickEvent) {
        setLeftColor(Color.White.takeIf { event == TabClickEvent.LEFT } ?: Color.Transparent)
        setRightColor(Color.White.takeIf { event == TabClickEvent.RIGHT } ?: Color.Transparent)
        onClick(event)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(buttonColor, shape = RoundedCornerShape(25.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            modifier = Modifier
                .padding(8.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    handleOnClick(TabClickEvent.LEFT)
                },
            shape = RoundedCornerShape(25.dp),
            color = leftColor
        ) {
            Text(
                text = leftTitle,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(10.dp)
            )
        }

        Surface(
            modifier = Modifier
                .padding(8.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    handleOnClick(TabClickEvent.RIGHT)
                },
            shape = RoundedCornerShape(25.dp),
            color = rightColor,
        ) {
            Text(
                text = rightTitle,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(10.dp)
            )
        }

    }
}


