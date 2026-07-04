package com.example.data.repository

import com.example.data.database.SettingsDao
import com.example.data.database.SettingsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(private val settingsDao: SettingsDao) {

    val settings: Flow<SettingsEntity> = settingsDao.getSettingsFlow()
        .map { it ?: SettingsEntity() }

    suspend fun updateChapter(chapterIndex: Int, pageIndex: Int) {
        val current = settingsDao.getSettings() ?: SettingsEntity()
        settingsDao.saveSettings(current.copy(
            lastChapterIndex = chapterIndex,
            lastPageIndex = pageIndex
        ))
    }

    suspend fun updateFontSize(size: Float) {
        val current = settingsDao.getSettings() ?: SettingsEntity()
        settingsDao.saveSettings(current.copy(fontSize = size))
    }

    suspend fun updateTheme(isDarkMode: Boolean) {
        val current = settingsDao.getSettings() ?: SettingsEntity()
        settingsDao.saveSettings(current.copy(isDarkMode = isDarkMode))
    }

    suspend fun updateScrollMode(isContinuousScroll: Boolean) {
        val current = settingsDao.getSettings() ?: SettingsEntity()
        settingsDao.saveSettings(current.copy(isContinuousScroll = isContinuousScroll))
    }
}
