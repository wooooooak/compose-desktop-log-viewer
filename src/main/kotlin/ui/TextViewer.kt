package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.unit.dp
import asbestos
import clouds
import lynxWhite
import model.FocusedFile

@ExperimentalFoundationApi
@Composable
fun TextViewer(file: FocusedFile) {
    Box {
        val state = rememberLazyListState()
        val stringCount = file.searchedTextLines.size
        LazyColumnFor(file.searchedTextLines, state = state) { textLine ->
            val active = remember { mutableStateOf(false) }
            Row(modifier = Modifier.padding(vertical = 4.dp)
                .fillMaxWidth()
                .pointerMoveFilter(
                    onEnter = {
                        active.value = true
                        false
                    },
                    onExit = {
                        active.value = false
                        false
                    }
                )) {
                Box(
                    modifier = Modifier
                        .background(if (active.value) lynxWhite else asbestos)
                        .preferredWidth(45.dp)
                        .fillMaxHeight()
                ) {
                    Text(textLine.line.toString())
                }
                Text(
                    textLine.text,
                    modifier = Modifier.background(if (active.value) lynxWhite else clouds)
                )
            }
        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd)
                .fillMaxHeight()
                .preferredWidth(15.dp),
            adapter = rememberScrollbarAdapter(
                scrollState = state,
                itemCount = stringCount,
                averageItemSize = 50.dp // TextBox height + Spacer height
            ),
        )
    }
}