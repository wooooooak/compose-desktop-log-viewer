package ui

import activeBlue
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import model.FocusedFile
import java.io.File

@Composable
fun ViewerTabsView(
    files: List<File>,
    focusedFile: FocusedFile?,
    onClickTab: (File) -> Unit,
    onClickDelete: (File) -> Unit
) {
    if (files.isNotEmpty()) {
        ScrollableTabRow(
            focusedFile?.index ?: 0,
            edgePadding = 0.dp,
            modifier = Modifier.preferredHeight(45.dp),
            backgroundColor = Color(52, 73, 94),
            contentColor = activeBlue
        ) {
            files.forEach {
                ViewerTab(it, onClickTab, onClickDelete)
            }
        }
    }
}

@Composable
fun ViewerTab(file: File, onClickTab: (File) -> Unit, onClickDelete: (File) -> Unit) {
    Row(modifier = Modifier
        .padding(start = 4.dp)
        .clickable { onClickTab(file) }
    ) {
        val active = remember { mutableStateOf(false) }
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight()) {
            Text(
                file.name,
                maxLines = 1,
                color = Color(236, 240, 241),
                textAlign = TextAlign.Center
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxHeight().preferredWidth(30.dp)
        ) {
            Button(
                onClick = { onClickDelete(file) },
                modifier = Modifier.preferredHeight(40.dp)
                    .pointerMoveFilter(onEnter = {
                        active.value = true
                        false
                    }, onExit = {
                        active.value = false
                        false
                    }),
                colors = ButtonConstants.defaultButtonColors(
                    backgroundColor = if (active.value) activeBlue else Color(52, 73, 94),
                    contentColor = Color(189, 195, 199)
                ),
                contentPadding = PaddingValues()
            ) {
                Text("x")
            }
        }
    }
}
