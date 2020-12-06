package ui

import LogViewerViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
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
            .background(color = Color(1, 100, 4))
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
            fileList.forEach {
                it?.let {
                    Text(it.absolutePath)
                }
            }
        }
    }
}
