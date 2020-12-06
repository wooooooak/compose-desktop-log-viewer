package ui

import LogViewerViewModel
import activeBlue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import clouds
import model.FocusedFile

@Composable
fun LogViewerWindow(logViewerViewModel: LogViewerViewModel) {
    val fileList = logViewerViewModel.fileList.value
    val focusedFile: FocusedFile? = logViewerViewModel.focusedFile.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = clouds)
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
                Box(modifier = Modifier.background(clouds)) {
                    TextViewer(focusedFile)
                    SearchTextView(logViewerViewModel.searchText.value) {
                        logViewerViewModel.searchLine(it)
                    }
                }
            }
        }
    }
}