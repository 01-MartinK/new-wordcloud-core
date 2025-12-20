package com.mk.wordcloud.controller

import com.mk.wordcloud.model.TagCloud
import com.mk.wordcloud.service.TagCloudService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api")
class TagCloudController(private val tagCloudService: TagCloudService) {
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
     fun uploadTextFile(@RequestBody file: MultipartFile): ResponseEntity<Long> {
        return ResponseEntity.ok(32)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getTagCloud(@PathVariable("id") id: String): ResponseEntity<TagCloud> = tagCloudService.getEntry(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTagCloud(@PathVariable("id") id: String): ResponseEntity<Void> = tagCloudService.deleteEntry(id)
}