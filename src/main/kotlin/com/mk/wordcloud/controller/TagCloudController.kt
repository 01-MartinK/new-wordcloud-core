package com.mk.wordcloud.controller

import com.mk.wordcloud.model.TagCloud
import com.mk.wordcloud.service.StorageService
import com.mk.wordcloud.service.TagCloudService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api")
class TagCloudController(private val tagCloudService: TagCloudService, private val storageService: StorageService) {
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    fun uploadTextFile(@RequestBody file: MultipartFile): ResponseEntity<String> {

        storageService.store(file)

        return ResponseEntity.ok("test")
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getTagCloud(@PathVariable("id") id: String): ResponseEntity<TagCloud> = tagCloudService.getEntry(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTagCloud(@PathVariable("id") id: String): ResponseEntity<Void> = tagCloudService.deleteEntry(id)
}