package ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextView(
    text: String,
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit = {}
) {
    Box(contentAlignment = Alignment.CenterEnd, modifier = modifier.fillMaxWidth().padding(end = 20.dp)) {
        TextField(text, onValueChange = { onTextChanged(it) }, placeholder = { Text("검색어 입력") })
    }
}