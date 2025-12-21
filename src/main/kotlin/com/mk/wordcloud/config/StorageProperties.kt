package com.mk.wordcloud.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("storage")
data class StorageProperties(
    var location: String = "upload-dir"
)