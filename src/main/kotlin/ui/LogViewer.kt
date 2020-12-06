package ui

import LogViewerViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import model.FocusedFile
import java.io.File

@Composable
fun LogViewerWindow(logViewerViewModel: LogViewerViewModel) {
    val fileList = logViewerViewModel.fileList.value
    val focusedFile: FocusedFile? = logViewerViewModel.focusedFile.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(1, 100, 4))
    ) {
        Column {
            ViewerTabsView(fileList.filterNotNull(), focusedFile) { file ->
                logViewerViewModel.selectFile(file)
            }
            fileList.forEach {
                it?.let {
                    Text(it.absolutePath)
                }
            }
        }
    }
}

@Composable
fun ViewerTabsView(files: List<File>, focusedFile: FocusedFile?, onClickTab: (File) -> Unit) {
    if (files.isNotEmpty()) {
        ScrollableTabRow(
            focusedFile?.index ?: 0,
            edgePadding = 0.dp,
            modifier = Modifier.preferredHeight(45.dp)
        ) {
            files.forEach {
                ViewerTab(it, onClickTab)
            }
        }
    }
}

@Composable
fun ViewerTab(file: File, onClickTab: (File) -> Unit) {
    Row(modifier = Modifier
        .padding(horizontal = 8.dp)
        .clickable { onClickTab(file) }
    ) {
        Text(file.name, overflow = TextOverflow.Ellipsis, maxLines = 1)
    }
}
