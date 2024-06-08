package info.mrprogrammer.ui_manager.ui.compose.tag

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.mrprogrammer.ui_manager.ui.theme.buttonColor
import info.mrprogrammer.ui_manager.ui.theme.whiteWithTransparency

@Composable
fun Tag(
    icon: Int? = null,
    title: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    isSelected: Boolean? = null,
    onClick: ((String) -> Unit)? = null
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.secondary.takeIf { isSelected == true} ?: whiteWithTransparency,
        modifier = modifier.padding(end = 5.dp, start = 5.dp).border(
            width = 0.5.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(20.dp)
        ).clickable {
            onClick?.let { onClick(title) }
        }
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier.width(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = title,
                color = textColor,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 13.sp
            )
        }
    }
}