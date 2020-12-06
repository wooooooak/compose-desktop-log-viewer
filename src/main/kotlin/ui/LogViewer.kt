package ui

import LogViewerViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.em

@Composable
fun LogViewerWindow(logViewerViewModel: LogViewerViewModel) {
    Box(modifier = Modifier.fillMaxSize().background(color = Color(1,100,4))) {
        Text(logViewerViewModel.file.value?.absolutePath ?: "", fontSize = 3.em)
    }
}