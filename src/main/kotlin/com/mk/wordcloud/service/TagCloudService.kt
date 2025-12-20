package com.mk.wordcloud.service

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.mk.wordcloud.model.TagCloud
import com.mk.wordcloud.repository.TagCloudRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TagCloudService(private val tagCloudRepository: TagCloudRepository) {
    fun getEntry(id: Long): ResponseEntity<TagCloud> = tagCloudRepository.findById(id)
        .map { tagCloud -> ResponseEntity.ok(tagCloud) }
        .orElse(ResponseEntity.notFound().build())

    fun createEntry(): ResponseEntity<TagCloud> = ResponseEntity.ok(
        tagCloudRepository.save(
            TagCloud(NanoIdUtils.randomNanoId())
        )
    )

    fun deleteEntry(id: Long): ResponseEntity<Void> = tagCloudRepository.findById(id)
        .map {
            tagCloud -> tagCloudRepository.delete(tagCloud)
            ResponseEntity<Void>(HttpStatus.ACCEPTED)
        }.orElse(ResponseEntity.notFound().build())
}