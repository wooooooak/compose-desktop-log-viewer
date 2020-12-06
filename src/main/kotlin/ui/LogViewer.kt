package ui

import LogViewerViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            if (focusedFile != null) {
                Box {
                    TextViewer(focusedFile)
                    SearchTextView(logViewerViewModel.searchText.value) {
                        logViewerViewModel.searchLine(it)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchTextView(text: String, onTextChanged: (String) -> Unit) {
    TextField(text, onValueChange = { onTextChanged(it) })
}

