package com.example.boredapp.model

import java.util.*


class NoteModel(private val title: String, private var text: String, private val link: String="") {
    //todo
    private var timeLastChange: String = Date().time.toString()
    private val id: Int

    init {
        id = 0
    }

    fun getTitle(): String = title
    fun getText(): String = text
    fun getInfo(): String {
        if (text.length > 55) {
            return text.substring(0, 52) + "..."
        } else {
            return text
        }
    }
}