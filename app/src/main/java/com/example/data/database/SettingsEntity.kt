package com.example.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_settings")
data class SettingsEntity(
    @PrimaryKey val id: Int = 0,
    val lastChapterIndex: Int = 0,
    val lastPageIndex: Int = 0,
    val fontSize: Float = 16f,
    val isDarkMode: Boolean = true,
    val isContinuousScroll: Boolean = false
)
