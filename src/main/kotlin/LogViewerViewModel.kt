import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import java.io.File

class LogViewerViewModel {
    private val _file = mutableStateOf<File?>(null)
    val file: State<File?> = _file

    fun addFile(file: File) {
        _file.value = file
    }
}