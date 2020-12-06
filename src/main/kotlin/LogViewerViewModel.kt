import androidx.compose.runtime.mutableStateOf
import model.FocusedFile
import model.TextLine
import java.io.File

class LogViewerViewModel {
    var fileList = mutableStateOf<List<File>>(listOf())
        private set

    var focusedFile = mutableStateOf<FocusedFile?>(null)
        private set

    var searchText = mutableStateOf("")
        private set

    fun addFile(file: File) {
        if (file !in fileList.value) {
            fileList.value = fileList.value + file
            val textLines = file.readLines().mapIndexed { index, s -> TextLine(index, s) }
            focusedFile.value = FocusedFile(file, fileList.value.indexOf(file), textLines ,textLines)
        }
    }

    fun selectFile(file: File) {
        val textLines = file.readLines().mapIndexed { index, s -> TextLine(index, s) }
        focusedFile.value = FocusedFile(file, fileList.value.indexOf(file), textLines, textLines)
    }

    fun deleteFile(file: File) {
        val newFileList = fileList.value - file
        if (newFileList.isEmpty()) {
            focusedFile.value = null
            fileList.value = listOf()
            return
        }
        val focusedFileIndex = focusedFile.value?.index ?: 0
        if (file == focusedFile.value?.file) { // 현재 보고있는 창을 닫으면 바로 이전 아니면 다음 탭으로 이동.
            when {
                focusedFileIndex - 1 < 0 -> {
                    focusedFile.value = FocusedFile(fileList.value?.get(focusedFileIndex + 1), focusedFileIndex)
                }
                focusedFileIndex - 1 >= 0 -> {
                    focusedFile.value = FocusedFile(fileList.value?.get(focusedFileIndex - 1), focusedFileIndex - 1)
                }
            }
        } else {
            val deleteFileIndex = fileList.value?.indexOf(file)
            if (deleteFileIndex < focusedFileIndex) {
                focusedFile.value = focusedFile.value?.copy(index = focusedFileIndex - 1)
            }
        }
        fileList.value = newFileList
    }

    fun searchLine(text: String) {
        searchText.value = text
        val fullTextLines = focusedFile.value?.fullTextLines ?: listOf()
        focusedFile.value = if (text.isBlank()) {
            focusedFile.value?.copy(searchedTextLines = fullTextLines)
        } else {
            focusedFile.value?.copy(searchedTextLines = fullTextLines.filter { text in it.text })
        }
    }
}
