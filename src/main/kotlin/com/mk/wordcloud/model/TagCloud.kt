package com.mk.wordcloud.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

enum class STATUS {
    Pending,
    InProgress,
    Complete
}

@Entity
@Table(name = "tagclouds")
data class TagCloud(
    @Id
    private val id: String,
    private val status: STATUS = STATUS.Pending,
    private val wordList: String = "",
)


