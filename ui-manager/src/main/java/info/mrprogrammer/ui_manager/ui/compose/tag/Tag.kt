package info.mrprogrammer.ui_manager.ui.compose.tag

import androidx.compose.foundation.Image
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
import info.mrprogrammer.ui_manager.ui.theme.whiteWithTransparency

@Composable
fun Tag(icon: Int, title: String, modifier: Modifier = Modifier) {

    Surface(shape = RoundedCornerShape(20.dp), color = whiteWithTransparency, modifier = modifier) {
        Row(modifier = Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = icon), contentDescription = "", modifier = Modifier.width(20.dp))
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = title, color = Color.White, style = MaterialTheme.typography.titleMedium, fontSize = 13.sp)
        }
    }
}