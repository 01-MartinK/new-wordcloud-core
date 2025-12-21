package com.mk.wordcloud.service

import org.springframework.web.multipart.MultipartFile

interface StorageService {
    fun init();
    fun store(file: MultipartFile, id: String): String;
    fun deleteAll();
}