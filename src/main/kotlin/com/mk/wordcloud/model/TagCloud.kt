package com.mk.wordcloud.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

enum class STATUS {
    Pending,
    InProgress,
    Complete
}

@Entity
@Table(name = "tagclouds")
data class TagCloud(
    @Id
    val id: String,
    val status: STATUS = STATUS.Pending,
    @JdbcTypeCode(SqlTypes.JSON)
    private val wordList: Map<String, Number> = emptyMap(),
)


