package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.unit.dp
import asbestos
import clouds
import lynxWhite
import model.FocusedFile

@Composable
fun TextViewer(file: FocusedFile) {
    LazyColumnFor(file.searchedTextLines) { textLine ->
        val active = remember { mutableStateOf(false) }
        Row(modifier = Modifier.padding(vertical = 4.dp)) {
            Box(
                modifier = Modifier
                    .background(if (active.value) lynxWhite else asbestos)
                    .preferredWidth(45.dp)
                    .fillMaxHeight()
            ) {
                Text(textLine.line.toString())
            }
            Text(textLine.text, modifier = Modifier
                .background(if (active.value) lynxWhite else clouds)
                .pointerMoveFilter(
                onEnter = {
                    active.value = true
                    false
                },
                onExit = {
                    active.value = false
                    false
                }
            ))
        }
    }
}