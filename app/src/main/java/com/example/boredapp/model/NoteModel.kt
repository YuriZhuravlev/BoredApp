package com.example.boredapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "note")
data class NoteModel(
        var title: String = "",
        var text: String = "",
        var link: String = "",
        var time: Long? = null,
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
) {
    fun getInfo(): String {
        if (text.length > 55) {
            return text.substring(0, 52) + "..."
        } else {
            return text
        }
    }
}