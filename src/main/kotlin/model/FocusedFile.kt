package model

import java.io.File

data class FocusedFile(
    val file: File,
    val index: Int,
    val fullTextLines: List<TextLine> = listOf(),
    val searchedTextLines: List<TextLine> = listOf(),
)

data class TextLine(
    val line: Int,
    val text: String
)