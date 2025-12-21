package com.mk.wordcloud.service

import com.mk.wordcloud.model.StorageException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class FileSystemStorageService(
    @Value($$"${spring.storage.location:/app/upload-dir}") private val storageLocation: String
) : StorageService {
    private val rootLocation: Path

    init {
        if (storageLocation.trim().isEmpty()) {
            throw StorageException("File upload location can not be Empty.")
        }
        this.rootLocation = Paths.get(storageLocation)
        if (Files.exists(rootLocation).not()) {
            Files.createDirectory(rootLocation)
        }
    }

    override fun store(file: MultipartFile, id: String): String {
        try {
            if (file.isEmpty) {
                throw StorageException("Failed to store empty file.")
            }
            val filename = file.originalFilename
                ?: throw StorageException("Failed to store file with null filename")

            val destinationFile = rootLocation.resolve("${id}_$filename")
                .normalize().toAbsolutePath()

            // Security check - prevent directory traversal
            if (!destinationFile.parent.equals(rootLocation.toAbsolutePath())) {
                throw StorageException("Cannot store file outside current directory.")
            }

            file.inputStream.use { inputStream ->
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING)
            }

            return "${id}_$filename"
        } catch (e: IOException) {
            throw StorageException("Failed to store file ${file.originalFilename}", e)
        }
    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation)
    }

    override fun init() {
        try {
            Files.createDirectories(rootLocation)
        } catch (e: IOException) {
            throw StorageException("Could not initialize storage", e)
        }
    }

}