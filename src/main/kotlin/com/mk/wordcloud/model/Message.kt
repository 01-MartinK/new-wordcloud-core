package com.mk.wordcloud.model

import kotlinx.serialization.Serializable

@Serializable
data class ParseRequest(
    val id: String,
    val content: String,
)

@Serializable
data class Message(
    val content: ParseRequest,
    val sender: String,
) {
    override fun toString(): String {
        return "Message(content='$content', sender='$sender')"
    }
}