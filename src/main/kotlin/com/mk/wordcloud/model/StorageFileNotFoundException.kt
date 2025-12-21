package com.mk.wordcloud.model

class StorageFileNotFoundException(message: String, cause: Throwable? = null) :
    StorageException(message, cause)