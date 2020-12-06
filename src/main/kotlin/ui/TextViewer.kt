package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.FocusedFile

@Composable
fun TextViewer(file: FocusedFile) {
    LazyColumnFor(file.searchedTextLines) { textLine ->
        Row(modifier = Modifier.padding(vertical = 4.dp)) {
            Box(
                modifier = Modifier
                    .background(Color(127, 140, 141))
                    .preferredWidth(45.dp)
                    .fillMaxHeight()
            ) {
                Text(textLine.line.toString())
            }
            Text(textLine.text)
        }
    }
}