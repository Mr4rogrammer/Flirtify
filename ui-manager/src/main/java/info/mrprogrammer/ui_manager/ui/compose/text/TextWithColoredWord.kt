package info.mrprogrammer.ui_manager.ui.compose.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle

@Composable
fun textWithColoredWord(
    text: String,
    highlightedWord: String,
    highlightColor: Color,
): AnnotatedString {
    val annotatedString = buildAnnotatedString {
        val startIndex = text.indexOf(highlightedWord)
        val endIndex = startIndex + highlightedWord.length

        append(text.substring(0, startIndex))
        withStyle(style = SpanStyle(color = highlightColor)) {
            append(text.substring(startIndex, endIndex))
        }
        append(text.substring(endIndex))
    }

    return annotatedString
}