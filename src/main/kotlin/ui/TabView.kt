package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Button
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import model.FocusedFile
import java.io.File

@Composable
fun ViewerTabsView(files: List<File>, focusedFile: FocusedFile?, onClickTab: (File) -> Unit, onClickDelete: (File) -> Unit) {
    if (files.isNotEmpty()) {
        ScrollableTabRow(
            focusedFile?.index ?: 0,
            edgePadding = 0.dp,
            modifier = Modifier.preferredHeight(45.dp)
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
        .padding(horizontal = 8.dp)
        .clickable { onClickTab(file) }
    ) {
        Text(file.name, overflow = TextOverflow.Ellipsis, maxLines = 1)
        Button(onClick = {onClickDelete(file)}) {
            Text("X")
        }
    }
}
