import androidx.compose.runtime.mutableStateOf
import model.FocusedFile
import java.io.File

class LogViewerViewModel {
    var fileList = mutableStateOf<List<File?>>(listOf())
        private set

    var focusedFile = mutableStateOf<FocusedFile?>(null)
        private set

    fun addFile(file: File) {
        if (file !in fileList.value) {
            fileList.value = fileList.value + file
            focusedFile.value = FocusedFile(file, fileList.value.indexOf(file))
        }
    }

    fun selectFile(file: File) {
        focusedFile.value = FocusedFile(file, fileList.value.indexOf(file))
    }
}
