import androidx.compose.desktop.ComposePanel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.em
import ui.LogViewerWindow
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.datatransfer.DataFlavor
import java.awt.dnd.DnDConstants
import java.awt.dnd.DropTarget
import java.awt.dnd.DropTargetDropEvent
import java.io.File
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.SwingUtilities.invokeLater
import javax.swing.WindowConstants


val logViewerViewModel = LogViewerViewModel()

fun main() = invokeLater {
    val window = JFrame()
    val composePanel = ComposePanel()
    window.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    window.title = "Log viewer"

    window.contentPane.add(targetZone(object : DropTarget() {
        override fun drop(event: DropTargetDropEvent) {
            try {
                event.acceptDrop(DnDConstants.ACTION_REFERENCE)
                val droppedFiles = event.transferable.getTransferData(DataFlavor.javaFileListFlavor) as List<*>
                droppedFiles.forEach {
                    if (it is File && it.extension in setOf("txt", "md")) {
                        logViewerViewModel.addFile(it)
                    }
                }
            } catch (e: Exception) {
                println(event)
            }
        }
    }), BorderLayout.WEST)
    window.contentPane.add(composePanel)
    composePanel.setContent {
        LogViewerWindow(logViewerViewModel)
    }
    window.setSize(1400, 1000)
    window.isVisible = true
}

private fun targetZone(target: DropTarget): JButton {
    return JButton().apply {
        toolTipText = "ToolTip!"
        text = "Drop files"
        preferredSize = Dimension(200, 100)
        dropTarget = target
    }
}
