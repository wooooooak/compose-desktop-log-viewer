package model

import java.io.File

data class FocusedFile(
    val file: File,
    val index: Int,
    val textLines: List<TextLine> = listOf()
)

data class TextLine(
    val line: Int,
    val text: String
)