package com.mk.wordcloud.service

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.mk.wordcloud.model.TagCloud
import com.mk.wordcloud.repository.TagCloudRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TagCloudService(private val tagCloudRepository: TagCloudRepository) {
    fun getEntry(id: String): ResponseEntity<TagCloud> {
        val tagCloud = tagCloudRepository.findAll().find { it.id == id } ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(tagCloud)
    }

    fun createEntry(): ResponseEntity<TagCloud> = ResponseEntity.ok(
        tagCloudRepository.save(
            TagCloud(NanoIdUtils.randomNanoId())
        )
    )

    fun deleteEntry(id: String): ResponseEntity<Void> {
        val tagCloud = tagCloudRepository.findAll().find { it.id == id } ?: return ResponseEntity.notFound().build()
        tagCloudRepository.delete(tagCloud)
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}