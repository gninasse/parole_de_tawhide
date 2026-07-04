package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.BookData
import com.example.data.Chapter
import com.example.ui.viewmodel.BookViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

// Custom Theme Colors (Professional Polish Theme: Warm Off-White & Deep Royal Amethyst)
object ReaderTheme {
    val CreamBackground = Color(0xFFFCF8F7)
    val CreamSurface = Color(0xFFFFFFFF)
    val CreamText = Color(0xFF1D1B1E)
    val CreamTextSecondary = Color(0xFF49454F)
    val CreamPrimary = Color(0xFF6750A4)
    val CreamAccent = Color(0xFFEADDFF)

    val MidnightBackground = Color(0xFF0B0813)
    val MidnightSurface = Color(0xFF131021)
    val MidnightText = Color(0xFFE6E1E5)
    val MidnightTextSecondary = Color(0xFF938F99)
    val MidnightPrimary = Color(0xFFD0BCFF)
    val MidnightAccent = Color(0xFF4F378B)
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreen(
    viewModel: BookViewModel,
    modifier: Modifier = Modifier
) {
    val settings by viewModel.settings.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    // Resolve active colors
    val backgroundColor = if (settings.isDarkMode) ReaderTheme.MidnightBackground else ReaderTheme.CreamBackground
    val surfaceColor = if (settings.isDarkMode) ReaderTheme.MidnightSurface else ReaderTheme.CreamSurface
    val textColor = if (settings.isDarkMode) ReaderTheme.MidnightText else ReaderTheme.CreamText
    val textSecondaryColor = if (settings.isDarkMode) ReaderTheme.MidnightTextSecondary else ReaderTheme.CreamTextSecondary
    val primaryColor = if (settings.isDarkMode) ReaderTheme.MidnightPrimary else ReaderTheme.CreamPrimary
    val dividerColor = (if (settings.isDarkMode) Color(0xFF2D2A37) else Color(0xFFE6E1E5))

    // Animated background and surface color transition for premium look
    val animatedBackground by animateColorAsState(targetValue = backgroundColor, animationSpec = tween(400), label = "background")
    val animatedSurface by animateColorAsState(targetValue = surfaceColor, animationSpec = tween(400), label = "surface")

    // Overlay control panel state (toggled when clicking middle of screen)
    var showOverlays by remember { mutableStateOf(true) }
    var showTOC by remember { mutableStateOf(false) }
    var showSettingsPanel by remember { mutableStateOf(false) }
    var bookmarkedChapters by remember { mutableStateOf(setOf<Int>()) }

    // Chapters count
    val totalChapters = BookData.chapters.size

    // Shared layout
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(animatedBackground)
    ) {
        if (settings.isContinuousScroll) {
            // === CONTINUOUS SCROLL MODE ===
            val lazyListState = rememberLazyListState()

            // Handle auto-restore position on first launch
            var isInitialScrollDone by remember { mutableStateOf(false) }
            LaunchedEffect(settings.lastChapterIndex) {
                if (!isInitialScrollDone && settings.lastChapterIndex in 0 until totalChapters) {
                    lazyListState.scrollToItem(settings.lastChapterIndex)
                    isInitialScrollDone = true
                }
            }

            // Sync scroll state back to DB
            val firstVisibleItemIndex by remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
            LaunchedEffect(firstVisibleItemIndex) {
                if (isInitialScrollDone && firstVisibleItemIndex in 0 until totalChapters) {
                    viewModel.updateReadingPosition(firstVisibleItemIndex, 0)
                }
            }

            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { showOverlays = !showOverlays }
                    .padding(horizontal = 16.dp)
                    .testTag("continuous_scroll_list")
            ) {
                // Header spacer for top status/navigation bar
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                    // Book Hero Banner
                    BookHeroBanner(
                        isDarkMode = settings.isDarkMode,
                        primaryColor = primaryColor,
                        textSecondaryColor = textSecondaryColor
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                itemsIndexed(BookData.chapters) { index, chapter ->
                    ChapterCard(
                        chapter = chapter,
                        fontSize = settings.fontSize,
                        textColor = textColor,
                        textSecondaryColor = textSecondaryColor,
                        primaryColor = primaryColor,
                        surfaceColor = animatedSurface,
                        dividerColor = dividerColor
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }

                // Footer spacer
                item {
                    Spacer(modifier = Modifier.height(120.dp))
                }
            }

            // External navigation buttons for easy hopping in scroll mode
            AnimatedVisibility(
                visible = showOverlays,
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 90.dp, start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val currentChap = settings.lastChapterIndex
                    IconButton(
                        onClick = {
                            if (currentChap > 0) {
                                coroutineScope.launch {
                                    lazyListState.animateScrollToItem(currentChap - 1)
                                }
                            }
                        },
                        enabled = currentChap > 0,
                        modifier = Modifier
                            .background(animatedSurface, RoundedCornerShape(50))
                            .size(48.dp)
                            .testTag("scroll_prev_chapter_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Chapitre Précédent",
                            tint = if (currentChap > 0) primaryColor else textSecondaryColor.copy(alpha = 0.5f)
                        )
                    }

                    Card(
                        colors = CardDefaults.cardColors(containerColor = animatedSurface.copy(alpha = 0.9f)),
                        shape = RoundedCornerShape(50),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = "Chapitre ${currentChap + 1} / $totalChapters",
                            color = textColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }

                    IconButton(
                        onClick = {
                            if (currentChap < totalChapters - 1) {
                                coroutineScope.launch {
                                    lazyListState.animateScrollToItem(currentChap + 1)
                                }
                            }
                        },
                        enabled = currentChap < totalChapters - 1,
                        modifier = Modifier
                            .background(animatedSurface, RoundedCornerShape(50))
                            .size(48.dp)
                            .testTag("scroll_next_chapter_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Chapitre Suivant",
                            tint = if (currentChap < totalChapters - 1) primaryColor else textSecondaryColor.copy(alpha = 0.5f)
                        )
                    }
                }
            }

        } else {
            // === PAGE BY PAGE SWIPE MODE ===
            val pagerState = rememberPagerState(
                initialPage = settings.lastChapterIndex.coerceIn(0, totalChapters - 1),
                pageCount = { totalChapters }
            )

            // Handle auto-restore position on first launch or TOC jump
            LaunchedEffect(settings.lastChapterIndex) {
                if (pagerState.currentPage != settings.lastChapterIndex && settings.lastChapterIndex in 0 until totalChapters) {
                    pagerState.scrollToPage(settings.lastChapterIndex)
                }
            }

            // Sync page change back to database
            LaunchedEffect(pagerState.currentPage) {
                viewModel.updateReadingPosition(pagerState.currentPage, 0)
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("chapter_pager")
            ) { pageIndex ->
                val chapter = BookData.chapters[pageIndex]
                val pageScrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(pageScrollState)
                        .clickable { showOverlays = !showOverlays }
                        .padding(horizontal = 20.dp)
                ) {
                    // Spacer for TopBar
                    Spacer(modifier = Modifier.height(100.dp))

                    if (pageIndex == 0) {
                        // First page shows the Cover art banner
                        BookHeroBanner(
                            isDarkMode = settings.isDarkMode,
                            primaryColor = primaryColor,
                            textSecondaryColor = textSecondaryColor
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Chapter Title Container
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "CHAPITRE ${chapter.id}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryColor,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = chapter.title,
                            fontSize = (settings.fontSize * 1.3f).sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.Serif
                        )
                        if (chapter.subtitle.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = chapter.subtitle,
                                fontSize = (settings.fontSize * 0.9f).sp,
                                color = textSecondaryColor,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(color = dividerColor, thickness = 1.dp)
                    }

                    // Chapter Content Paragraphs (Reflowable text adapting to fontSize)
                    chapter.paragraphs.forEachIndexed { pIndex, paragraph ->
                        val isVerse = paragraph.startsWith("{") || paragraph.startsWith("«")
                        val alignment = if (isVerse) TextAlign.Center else TextAlign.Justify
                        val weight = if (isVerse) FontWeight.Medium else FontWeight.Normal
                        val styleFamily = if (isVerse) FontFamily.Serif else FontFamily.Default

                        Text(
                            text = paragraph,
                            fontSize = settings.fontSize.sp,
                            lineHeight = (settings.fontSize * 1.55f).sp,
                            fontWeight = weight,
                            fontFamily = styleFamily,
                            color = if (isVerse) primaryColor else textColor,
                            textAlign = alignment,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        )
                    }

                    // Page End Navigation
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (pageIndex > 0) {
                            OutlinedButton(
                                onClick = {
                                    coroutineScope.launch { pagerState.animateScrollToPage(pageIndex - 1) }
                                },
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = primaryColor),
                                modifier = Modifier.testTag("pager_prev_button")
                            ) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Précédent", fontSize = 12.sp)
                            }
                        } else {
                            Spacer(modifier = Modifier.width(1.dp))
                        }

                        if (pageIndex < totalChapters - 1) {
                            Button(
                                onClick = {
                                    coroutineScope.launch { pagerState.animateScrollToPage(pageIndex + 1) }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = primaryColor, contentColor = animatedBackground),
                                modifier = Modifier.testTag("pager_next_button")
                            ) {
                                Text("Suivant", fontSize = 12.sp)
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        } else {
                            Spacer(modifier = Modifier.width(1.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(120.dp))
                }
            }
        }

        // === PERSISTENT READ-PROGRESS & INDICATOR BAR ===
        AnimatedVisibility(
            visible = showOverlays,
            enter = fadeIn() + slideInVertically { it },
            exit = fadeOut() + slideOutVertically { it },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Surface(
                color = animatedSurface,
                tonalElevation = 8.dp,
                shadowElevation = 12.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val progressPercent = ((settings.lastChapterIndex + 1).toFloat() / totalChapters.toFloat())
                    val readablePercent = (progressPercent * 100).toInt()

                    // --- Custom Slider / Progress row mimicking tailwind design ---
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "$readablePercent%",
                            color = textSecondaryColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(36.dp)
                        )

                        // Custom elegant Track with a thumb dot
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(dividerColor)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(progressPercent)
                                    .fillMaxHeight()
                                    .background(primaryColor)
                            )
                        }

                        Text(
                            text = "p. ${settings.lastChapterIndex + 1}",
                            color = textSecondaryColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(36.dp),
                            textAlign = TextAlign.End
                        )
                    }

                    // --- 4 Bottom menu buttons exactly like the HTML design ---
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 1. Table of Contents
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { showTOC = true }
                                .padding(vertical = 8.dp)
                                .testTag("bottom_nav_toc"),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = "Chapitres",
                                tint = primaryColor,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Chapitres",
                                color = textSecondaryColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // 2. Theme Toggle
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { viewModel.toggleTheme() }
                                .padding(vertical = 8.dp)
                                .testTag("bottom_nav_theme"),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.Contrast,
                                contentDescription = "Thème",
                                tint = primaryColor,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Thème",
                                color = textSecondaryColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // 3. Bookmark (Signet)
                        val isBookmarked = bookmarkedChapters.contains(settings.lastChapterIndex)
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    val currentChap = settings.lastChapterIndex
                                    bookmarkedChapters = if (isBookmarked) {
                                        bookmarkedChapters - currentChap
                                    } else {
                                        bookmarkedChapters + currentChap
                                    }
                                }
                                .padding(vertical = 8.dp)
                                .testTag("bottom_nav_bookmark"),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = "Signet",
                                tint = primaryColor,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isBookmarked) "Enregistré" else "Signet",
                                color = textSecondaryColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // 4. Options Toggle
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { showSettingsPanel = !showSettingsPanel }
                                .padding(vertical = 8.dp)
                                .testTag("bottom_nav_options"),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Options",
                                tint = primaryColor,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Options",
                                color = textSecondaryColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // Discrete tiny progress indicator when overlays are hidden for pure immersive reading
        AnimatedVisibility(
            visible = !showOverlays,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            val progressPercent = ((settings.lastChapterIndex + 1).toFloat() / totalChapters.toFloat())
            LinearProgressIndicator(
                progress = { progressPercent },
                color = primaryColor.copy(alpha = 0.5f),
                trackColor = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .navigationBarsPadding()
            )
        }

        // === TOP FLOATING APP BAR (Overlay) ===
        AnimatedVisibility(
            visible = showOverlays,
            enter = fadeIn() + slideInVertically { -it },
            exit = fadeOut() + slideOutVertically { -it }
        ) {
            Surface(
                color = animatedSurface.copy(alpha = 0.96f),
                tonalElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            ) {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                text = BookData.title,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = BookData.author,
                                fontSize = 11.sp,
                                color = textSecondaryColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { showTOC = true },
                            modifier = Modifier.testTag("toc_menu_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.List,
                                contentDescription = "Table des matières",
                                tint = primaryColor
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { viewModel.toggleTheme() },
                            modifier = Modifier.testTag("theme_toggle_button")
                        ) {
                            Icon(
                                imageVector = if (settings.isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                                contentDescription = "Changer le Thème",
                                tint = primaryColor
                            )
                        }
                        IconButton(
                            onClick = { showSettingsPanel = !showSettingsPanel },
                            modifier = Modifier.testTag("settings_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Réglages de lecture",
                                tint = primaryColor
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            }
        }

        // === SLIDING SETTINGS OVERLAY PANEL ===
        AnimatedVisibility(
            visible = showSettingsPanel && showOverlays,
            enter = slideInVertically { it } + fadeIn(),
            exit = slideOutVertically { it } + fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 75.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = animatedSurface),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("settings_panel")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Paramètres de lecture",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )
                        IconButton(
                            onClick = { showSettingsPanel = false },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Fermer", tint = textSecondaryColor, modifier = Modifier.size(18.dp))
                        }
                    }

                    HorizontalDivider(color = dividerColor, modifier = Modifier.padding(vertical = 12.dp))

                    // Font Size Controls (+ / -)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.FormatSize, contentDescription = null, tint = primaryColor, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Taille du texte", fontSize = 13.sp, color = textColor, fontWeight = FontWeight.Medium)
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            IconButton(
                                onClick = { viewModel.setFontSize(settings.fontSize - 1.5f) },
                                modifier = Modifier
                                    .background(dividerColor, RoundedCornerShape(8.dp))
                                    .size(36.dp)
                                    .testTag("font_decrease_button")
                            ) {
                                Icon(Icons.Default.Remove, contentDescription = "Diminuer", tint = textColor, modifier = Modifier.size(16.dp))
                            }

                            Text(
                                text = "${settings.fontSize.toInt()} sp",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColor,
                                modifier = Modifier.width(44.dp),
                                textAlign = TextAlign.Center
                            )

                            IconButton(
                                onClick = { viewModel.setFontSize(settings.fontSize + 1.5f) },
                                modifier = Modifier
                                    .background(dividerColor, RoundedCornerShape(8.dp))
                                    .size(36.dp)
                                    .testTag("font_increase_button")
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Augmenter", tint = textColor, modifier = Modifier.size(16.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Reading Style (Swipe Pager vs Continuous Scroll)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (settings.isContinuousScroll) Icons.Default.SwapVert else Icons.Default.SwapHoriz,
                                contentDescription = null,
                                tint = primaryColor,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Mode de défilement", fontSize = 13.sp, color = textColor, fontWeight = FontWeight.Medium)
                        }

                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(dividerColor)
                                .padding(2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (!settings.isContinuousScroll) primaryColor else Color.Transparent)
                                    .clickable { if (settings.isContinuousScroll) viewModel.toggleScrollMode() }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                                    .testTag("mode_page_button"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Page",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (!settings.isContinuousScroll) animatedBackground else textColor
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (settings.isContinuousScroll) primaryColor else Color.Transparent)
                                    .clickable { if (!settings.isContinuousScroll) viewModel.toggleScrollMode() }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                                    .testTag("mode_scroll_button"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Scroll",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (settings.isContinuousScroll) animatedBackground else textColor
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Theme Selection Shortcut
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (settings.isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                                contentDescription = null,
                                tint = primaryColor,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Thème de lecture", fontSize = 13.sp, color = textColor, fontWeight = FontWeight.Medium)
                        }

                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(dividerColor)
                                .padding(2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (!settings.isDarkMode) primaryColor else Color.Transparent)
                                    .clickable { if (settings.isDarkMode) viewModel.toggleTheme() }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                                    .testTag("theme_light_button"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Sépia",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (!settings.isDarkMode) animatedBackground else textColor
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(if (settings.isDarkMode) primaryColor else Color.Transparent)
                                    .clickable { if (!settings.isDarkMode) viewModel.toggleTheme() }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                                    .testTag("theme_dark_button"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Bleu Nuit",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (settings.isDarkMode) animatedBackground else textColor
                                )
                            }
                        }
                    }
                }
            }
        }

        // === TABLE OF CONTENTS MODAL BOTTOM SHEET ===
        if (showTOC) {
            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            ModalBottomSheet(
                onDismissRequest = { showTOC = false },
                sheetState = sheetState,
                containerColor = animatedSurface,
                dragHandle = {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .size(width = 40.dp, height = 4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(dividerColor)
                    )
                },
                modifier = Modifier.testTag("toc_bottom_sheet")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp, start = 20.dp, end = 20.dp)
                ) {
                    Text(
                        text = "Table des matières",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    HorizontalDivider(color = dividerColor, modifier = Modifier.padding(bottom = 12.dp))

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, fill = false)
                    ) {
                        itemsIndexed(BookData.chapters) { index, chapter ->
                            val isCurrent = index == settings.lastChapterIndex
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isCurrent) primaryColor.copy(alpha = 0.15f) else Color.Transparent)
                                    .clickable {
                                        viewModel.updateReadingPosition(index, 0)
                                        showTOC = false
                                        // hide settings and show panel to refresh reading view
                                        showOverlays = false
                                    }
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Rounded Index bubble
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(if (isCurrent) primaryColor else dividerColor),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${chapter.id}",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isCurrent) animatedBackground else textColor
                                    )
                                }

                                Spacer(modifier = Modifier.width(14.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = chapter.title,
                                        fontSize = 14.sp,
                                        fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isCurrent) primaryColor else textColor
                                    )
                                    if (chapter.subtitle.isNotEmpty()) {
                                        Text(
                                            text = chapter.subtitle,
                                            fontSize = 11.sp,
                                            color = textSecondaryColor,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }

                                if (isCurrent) {
                                    Icon(
                                        imageVector = Icons.Default.Book,
                                        contentDescription = "Actuel",
                                        tint = primaryColor,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // === FLOATING NAVIGATION ACTION BUTTONS ===
        AnimatedVisibility(
            visible = showOverlays,
            enter = fadeIn() + slideInVertically { it / 2 },
            exit = fadeOut() + slideOutVertically { it / 2 },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 180.dp, end = 20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val currentChap = settings.lastChapterIndex

                // Next Chapter Button
                IconButton(
                    onClick = {
                        if (currentChap < totalChapters - 1) {
                            viewModel.updateReadingPosition(currentChap + 1, 0)
                        }
                    },
                    enabled = currentChap < totalChapters - 1,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            if (currentChap < totalChapters - 1) primaryColor.copy(alpha = 0.2f) else dividerColor.copy(alpha = 0.5f)
                        )
                        .testTag("floating_next_chapter")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Suivant",
                        tint = if (currentChap < totalChapters - 1) primaryColor else textSecondaryColor.copy(alpha = 0.5f),
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Previous Chapter Button
                IconButton(
                    onClick = {
                        if (currentChap > 0) {
                            viewModel.updateReadingPosition(currentChap - 1, 0)
                        }
                    },
                    enabled = currentChap > 0,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            if (currentChap > 0) animatedSurface else dividerColor.copy(alpha = 0.5f)
                        )
                        .testTag("floating_prev_chapter")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Précédent",
                        tint = if (currentChap > 0) primaryColor else textSecondaryColor.copy(alpha = 0.5f),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

// Beautiful Banner at the top of the book / cover page
@Composable
fun BookHeroBanner(
    isDarkMode: Boolean,
    primaryColor: Color,
    textSecondaryColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkMode) Color(0xFF0F172A) else Color(0xFFF1ECE4)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Elegant Book icon
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(primaryColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Book,
                    contentDescription = null,
                    tint = primaryColor,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tafsir Kalimat ut-Tawheed",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor,
                letterSpacing = 1.5.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "L'Explication de la Parole d'Unicité",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (isDarkMode) Color.White else Color(0xFF2C241E),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Par l'Érudit Dr. Rabî' Ibn Hâdî Al-Madkhalî",
                fontSize = 12.sp,
                color = textSecondaryColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(
                color = primaryColor.copy(alpha = 0.3f),
                thickness = 1.dp,
                modifier = Modifier.width(60.dp)
            )
        }
    }
}

// Composable displaying a full single Chapter in Continuous Scroll mode
@Composable
fun ChapterCard(
    chapter: Chapter,
    fontSize: Float,
    textColor: Color,
    textSecondaryColor: Color,
    primaryColor: Color,
    surfaceColor: Color,
    dividerColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "CHAPITRE ${chapter.id}",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chapter.title,
                fontSize = (fontSize * 1.3f).sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                fontFamily = FontFamily.Serif
            )
            if (chapter.subtitle.isNotEmpty()) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = chapter.subtitle,
                    fontSize = (fontSize * 0.9f).sp,
                    color = textSecondaryColor,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = dividerColor, thickness = 1.dp)
            Spacer(modifier = Modifier.height(12.dp))

            chapter.paragraphs.forEachIndexed { pIndex, paragraph ->
                val isVerse = paragraph.startsWith("{") || paragraph.startsWith("«")
                val alignment = if (isVerse) TextAlign.Center else TextAlign.Justify
                val weight = if (isVerse) FontWeight.Medium else FontWeight.Normal
                val styleFamily = if (isVerse) FontFamily.Serif else FontFamily.Default

                Text(
                    text = paragraph,
                    fontSize = fontSize.sp,
                    lineHeight = (fontSize * 1.55f).sp,
                    fontWeight = weight,
                    fontFamily = styleFamily,
                    color = if (isVerse) primaryColor else textColor,
                    textAlign = alignment,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}
