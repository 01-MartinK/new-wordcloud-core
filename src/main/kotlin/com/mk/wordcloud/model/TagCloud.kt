package com.mk.wordcloud.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import kotlinx.serialization.Serializable
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

enum class Status {
    Pending,
    InProgress,
    Complete,
    Error
}

@Entity
@Table(name = "tagclouds")
@Serializable
data class TagCloud(
    @Id
    val id: String,
    val status: Status = Status.Pending,
    @JdbcTypeCode(SqlTypes.JSON)
    private val wordList: Map<String, Int> = emptyMap(),
)


