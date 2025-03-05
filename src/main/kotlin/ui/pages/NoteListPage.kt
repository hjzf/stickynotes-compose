package ui.pages

import LocalApplicationLocalization
import LocalProfileState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import logic.DataStore
import logic.NoteCard
import logic.ProfileState
import logic.getNoteColor
import ui.SvgIcons
import ui.components.ButtonWithIcon
import ui.icons.winClose
import ui.icons.winMore
import ui.icons.winSearch
import java.util.regex.Pattern

private fun calculateAnnotationText(text: String, searchText: String): AnnotatedString {
    return if (searchText.isEmpty()) {
        AnnotatedString(text = text)
    } else {
        buildAnnotatedString {
            append(text)
            Regex(Pattern.quote(searchText), RegexOption.IGNORE_CASE).findAll(text).forEach {
                addStyle(SpanStyle(background = Color.Yellow, color = Color.Black), it.range.first, it.range.last + 1)
            }
        }
    }
}

private suspend fun openNote(card: NoteCard, searchText: String) {
    if (searchText.isEmpty()) {
        DataStore.updateNoteVisible(
            card.noteId, true
        )
    } else {
        DataStore.updateNoteVisibleAndPosition(
            card.noteId, true, card.position1, card.position2
        )
    }
}

private suspend fun closeNote(card: NoteCard) {
    DataStore.updateNoteVisible(card.noteId, false)
}

private fun exportMarkdown(card: NoteCard) {
    DataStore.exportMarkdown(card.noteId)
}

private suspend fun deleteNote(card: NoteCard) {
    DataStore.deleteNote(card.noteId)
}

@Composable
fun NoteListPage(lazyListState: LazyListState = rememberLazyListState()) {
    val coroutineScope = rememberCoroutineScope()
    val searchText = remember { mutableStateOf("") }
    val cards = remember { mutableStateOf(DataStore.search("")) }
    LaunchedEffect(Unit) {
        DataStore.noteContentVersionFlow.collect {
            coroutineScope.launch {
                cards.value = DataStore.search(searchText.value)
            }
        }
    }
    LaunchedEffect(Unit) {
        DataStore.windowStateVersionFlow.collect {
            coroutineScope.launch {
                cards.value = DataStore.search(searchText.value)
            }
        }
    }
    val localProfileState = LocalProfileState.current
    val localApplicationLocalization = LocalApplicationLocalization.current
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = lazyListState, modifier = Modifier.fillMaxSize()
        ) {
            item {
                ContextMenuArea({
                    listOf(
                        ContextMenuItem(localApplicationLocalization.updateIndex) {
                            coroutineScope.launch {
                                DataStore.updateIndex()
                            }
                        }
                    )
                }) {
                    Text(
                        text = localApplicationLocalization.stickynotes,
                        modifier = Modifier.padding(horizontal = 12.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }
                Spacer(modifier = Modifier.height(13.dp))
            }
            stickyHeader {
                Column(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight().background(color = Color.White)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(start = 12.dp, end = 12.dp)
                            .background(color = Color(0xFFEEEEEE)),
                    ) {
                        SearchInput(
                            searchText = searchText.value,
                            onSearchTextChange = {
                                searchText.value = it
                                coroutineScope.launch {
                                    cards.value = DataStore.search(searchText.value)
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.fillMaxWidth().height(12.dp))
                }
            }
            itemsIndexed(cards.value, key = { _, it -> it.key }) { index, card ->
                NoteCard(index, cards.value.lastIndex, card, searchText.value, localProfileState)
            }
        }
        VerticalScrollbar(lazyListState)
    }
}

@Composable
private fun RowScope.SearchInput(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
) {
    val localProfileState = LocalProfileState.current
    val localApplicationLocalization = LocalApplicationLocalization.current
    val focusRequester = remember { FocusRequester() }
    BasicTextField(
        value = searchText,
        onValueChange = { onSearchTextChange(it) },
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp).weight(1f).height(20.dp)
            .focusRequester(focusRequester),
        singleLine = true,
        decorationBox = { innerTextField ->
            if (searchText.isEmpty()) {
                Text(
                    text = "${localApplicationLocalization.search}...",
                    color = Color(0xFFA0A0A0),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                )
            }
            innerTextField()
        })
    if (searchText.isNotEmpty()) {
        ButtonWithIcon(
            text = localApplicationLocalization.clear,
            iconSize = 22.dp,
            width = 32.dp,
            height = 32.dp,
            icon = SvgIcons.winClose(),
            showTooltip = localProfileState.tooltip,
            onClick = {
                onSearchTextChange("")
                focusRequester.requestFocus()
            }
        )
    }
    ButtonWithIcon(
        text = localApplicationLocalization.search,
        iconSize = 18.dp,
        width = 32.dp,
        height = 32.dp,
        icon = SvgIcons.winSearch(),
        showTooltip = localProfileState.tooltip,
        onClick = { }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun LazyItemScope.NoteCard(
    index: Int,
    lastIndex: Int,
    card: NoteCard,
    searchText: String,
    localProfileState: ProfileState,
) {
    val coroutineScope = rememberCoroutineScope()
    val expanded = remember { mutableStateOf(false) }
    val hoverInteractionSource = remember { MutableInteractionSource() }
    val extraEnterInteraction = remember { mutableStateOf<HoverInteraction.Enter?>(null) }
    LaunchedEffect(expanded.value) {
        if (expanded.value) {
            val enter = HoverInteraction.Enter()
            if (hoverInteractionSource.tryEmit(enter)) {
                extraEnterInteraction.value = enter
            }
        } else {
            if (extraEnterInteraction.value != null) {
                delay(200) // optimize user experience
                if (hoverInteractionSource.tryEmit(HoverInteraction.Exit(extraEnterInteraction.value!!))) {
                    extraEnterInteraction.value = null
                }
            }
        }
    }
    val hovered = hoverInteractionSource.collectIsHoveredAsState()
    val localApplicationLocalization = LocalApplicationLocalization.current
    val noteColor = remember(localProfileState.colorTheme, card.style) {
        getNoteColor(localProfileState.colorTheme, card.style)
    }
    Box(
        modifier = Modifier.padding(
            start = 12.dp, end = 12.dp, bottom = if (index == lastIndex) 12.dp else 9.dp
        ).fillMaxWidth().wrapContentHeight().animateItem()
            .hoverable(interactionSource = hoverInteractionSource, enabled = true),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().wrapContentHeight()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(20.dp).border(
                    width = 0.dp, color = Color.Transparent, shape = RoundedCornerShape(
                        topStart = 2.dp, topEnd = 2.dp, bottomEnd = 0.dp, bottomStart = 0.dp
                    )
                ).background(
                    color = noteColor.card, shape = RoundedCornerShape(
                        topStart = 2.dp, topEnd = 2.dp, bottomEnd = 0.dp, bottomStart = 0.dp
                    )
                ).padding(start = 8.dp, end = 8.dp),
            ) {
                Spacer(modifier = Modifier.weight(1f))
                if (!hovered.value) {
                    Text(
                        text = card.updateTime,
                        fontSize = 10.sp,
                        color = noteColor.font,
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().height(102.dp).background(color = noteColor.card)
                    .padding(start = 16.dp, end = 16.dp, bottom = 2.dp),
            ) {
                Text(
                    text = calculateAnnotationText(card.description, searchText),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = noteColor.font,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = when (localProfileState.fontFamily) {
                        FontFamily.Serif.name -> FontFamily.Serif
                        FontFamily.SansSerif.name -> FontFamily.SansSerif
                        FontFamily.Monospace.name -> FontFamily.Monospace
                        FontFamily.Cursive.name -> FontFamily.Cursive
                        else -> FontFamily.Default
                    },
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().height(14.dp)
            ) {
                Spacer(
                    modifier = Modifier.weight(1f).height(14.dp).border(
                        width = 0.dp, color = Color.Transparent, shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = if (card.visible) 0.dp else 2.dp,
                            bottomStart = 2.dp
                        )
                    ).background(
                        color = noteColor.card, shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = if (card.visible) 0.dp else 2.dp,
                            bottomStart = 2.dp
                        )
                    )
                )
                if (card.visible) {
                    Canvas(modifier = Modifier.width(14.dp).height(14.dp)) {
                        drawPath(
                            path = Path().apply {
                                moveTo(0.dp.toPx(), 0.dp.toPx())
                                lineTo(14.dp.toPx(), 0.dp.toPx())
                                lineTo(0.dp.toPx(), 14.dp.toPx())
                                close()
                            }, color = noteColor.overlap, style = Fill
                        )
                    }
                }
            }
        }
        if (hovered.value) {
            Column(
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
                    .combinedClickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        enabled = true,
                        onClickLabel = null,
                        role = null,
                        onClick = {},
                        onDoubleClick = {
                            coroutineScope.launch { openNote(card, searchText) }
                        }
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().height(36.dp).border(
                        width = 0.dp, color = Color.Transparent, shape = RoundedCornerShape(
                            topStart = 2.dp, topEnd = 2.dp, bottomEnd = 0.dp, bottomStart = 0.dp
                        )
                    ).background(
                        color = Color(0xd000000), shape = RoundedCornerShape(
                            topStart = 2.dp, topEnd = 2.dp, bottomEnd = 0.dp, bottomStart = 0.dp
                        )
                    )
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    ExposedDropdownMenuBox(
                        expanded = expanded.value,
                        onExpandedChange = { expanded.value = it },
                    ) {
                        ButtonWithIcon(
                            text = localApplicationLocalization.menu,
                            iconSize = 32.dp,
                            width = 36.dp,
                            height = 36.dp,
                            icon = SvgIcons.winMore(noteColor.font),
                            showTooltip = localProfileState.tooltip,
                            onClick = { expanded.value = true },
                        )
                        DropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false },
                        ) {
                            arrayOf(
                                if (card.visible) {
                                    "closeNote" to localApplicationLocalization.closeNote
                                } else {
                                    "openNote" to localApplicationLocalization.openNote
                                },
                                "exportMarkdown" to localApplicationLocalization.exportMarkdown,
                                "deleteNote" to localApplicationLocalization.deleteNote,
                            ).forEach { keyAndLabel ->
                                val itemInteractionSource = remember { MutableInteractionSource() }
                                val itemHovered = itemInteractionSource.collectIsHoveredAsState()
                                DropdownMenuItem(
                                    modifier = Modifier.hoverable(
                                        interactionSource = itemInteractionSource, enabled = true
                                    ).background(
                                        if (itemHovered.value) noteColor.base.copy(
                                            alpha = 0.1f
                                        ) else Color.Transparent
                                    ),
                                    onClick = {
                                        expanded.value = false
                                        if (keyAndLabel.first == "closeNote") {
                                            coroutineScope.launch { closeNote(card) }
                                        }
                                        if (keyAndLabel.first == "openNote") {
                                            coroutineScope.launch { openNote(card, searchText) }
                                        }
                                        if (keyAndLabel.first == "exportMarkdown") {
                                            coroutineScope.launch { exportMarkdown(card) }
                                        }
                                        if (keyAndLabel.first == "deleteNote") {
                                            coroutineScope.launch { deleteNote(card) }
                                        }
                                    },
                                ) {
                                    Text(
                                        text = keyAndLabel.second, color = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(
                    modifier = Modifier.fillMaxWidth().height(86.dp).background(
                        color = Color(0xd000000)
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth().height(14.dp)
                ) {
                    Spacer(
                        modifier = Modifier.weight(1f).height(14.dp).border(
                            width = 0.dp, color = Color.Transparent, shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomEnd = if (card.visible) 0.dp else 2.dp,
                                bottomStart = 2.dp
                            )
                        ).background(
                            color = Color(0xd000000), shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomEnd = if (card.visible) 0.dp else 2.dp,
                                bottomStart = 2.dp
                            )
                        )
                    )
                    if (card.visible) {
                        Spacer(
                            modifier = Modifier.width(14.dp).height(14.dp).background(color = Color.Transparent)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BoxScope.VerticalScrollbar(lazyListState: LazyListState) {
    val hoverInteractionSource = remember { MutableInteractionSource() }
    val hovered = hoverInteractionSource.collectIsHoveredAsState()
    val scrollbarVisible = remember { mutableStateOf(false) }
    LaunchedEffect(hovered.value) {
        if (hovered.value) {
            scrollbarVisible.value = true
        } else {
            delay(2000)
            scrollbarVisible.value = false
        }
    }
    Box(
        modifier = Modifier.Companion.align(Alignment.CenterEnd).width(12.dp).fillMaxHeight()
            .background(color = Color.Transparent)
            .hoverable(interactionSource = hoverInteractionSource, enabled = true),
    ) {
        VerticalScrollbar(
            adapter = rememberScrollbarAdapter(lazyListState),
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().background(color = Color.Transparent),
            style = ScrollbarStyle(
                minimalHeight = 10.dp,
                thickness = if (scrollbarVisible.value) 12.dp else 2.dp,
                shape = RoundedCornerShape(0.dp),
                hoverDurationMillis = 300,
                unhoverColor = Color(0xff8b8b8b),
                hoverColor = Color(0xff636363)
            )
        )
    }
}