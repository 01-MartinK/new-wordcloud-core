package com.mk.wordcloud.controller

import com.mk.wordcloud.model.Message
import com.mk.wordcloud.model.ParseRequest
import com.mk.wordcloud.model.TagCloud
import com.mk.wordcloud.service.MessageProducer
import com.mk.wordcloud.service.StorageService
import com.mk.wordcloud.service.TagCloudService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api")
class TagCloudController(
    private val tagCloudService: TagCloudService,
    private val storageService: StorageService,
    private val messageProducer: MessageProducer
) {
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    fun uploadTextFile(@RequestBody file: MultipartFile): ResponseEntity<String> {

        val entry = tagCloudService.createEntry()
        val fileName = storageService.store(file, entry.id)

        val parseRequest = ParseRequest(entry.id, fileName)
        val message = Message(content = parseRequest, sender = "CoreServer")

        messageProducer.sendMessage(message)

        return ResponseEntity.ok(entry.id)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getTagCloud(@PathVariable("id") id: String): ResponseEntity<TagCloud> {
        val entry = tagCloudService.getEntry(id)
        return if (entry != null) ResponseEntity.ok(entry) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTagCloud(@PathVariable("id") id: String): ResponseEntity<Void> {
        return if (tagCloudService.deleteEntry(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}