package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.database.AppDatabase
import com.example.data.database.SettingsEntity
import com.example.data.repository.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SettingsRepository

    init {
        val database = AppDatabase.getDatabase(application)
        repository = SettingsRepository(database.settingsDao())
    }

    val settings: StateFlow<SettingsEntity> = repository.settings
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SettingsEntity()
        )

    fun updateReadingPosition(chapterIndex: Int, pageIndex: Int) {
        viewModelScope.launch {
            repository.updateChapter(chapterIndex, pageIndex)
        }
    }

    fun setFontSize(size: Float) {
        viewModelScope.launch {
            val clampedSize = size.coerceIn(12f, 32f)
            repository.updateFontSize(clampedSize)
        }
    }

    fun toggleTheme() {
        viewModelScope.launch {
            val current = settings.value.isDarkMode
            repository.updateTheme(!current)
        }
    }

    fun toggleScrollMode() {
        viewModelScope.launch {
            val current = settings.value.isContinuousScroll
            repository.updateScrollMode(!current)
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BookViewModel(application) as T
        }
    }
}
