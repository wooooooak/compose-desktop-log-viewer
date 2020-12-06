package ui

import LogViewerViewModel
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
fun LogViewerWindow(logViewerViewModel: LogViewerViewModel) {
    val fileList = logViewerViewModel.fileList.value
    val focusedFile: FocusedFile? = logViewerViewModel.focusedFile.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(236, 240, 241))
    ) {
        Column {
            ViewerTabsView(fileList,
                focusedFile,
                onClickTab = { file ->
                    logViewerViewModel.selectFile(file)
                },
                onClickDelete = { file ->
                    logViewerViewModel.deleteFile(file)
                })
            if (focusedFile != null) TextViewer(focusedFile)
        }
    }
}

@Composable
fun TextViewer(file: FocusedFile) {
    LazyColumnFor(file.textLines) { textLine ->
        Row {
            Box(
                modifier = Modifier
                    .background(Color(127, 140, 141))
                    .preferredWidth(70.dp)
                    .fillMaxHeight()
            ) {
                Text(textLine.line.toString())
            }
            Text(textLine.text)
        }
    }
}