package coQ9kxZm.mk.wordcloud.service

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.mk.wordcloud.model.TagCloud
import com.mk.wordcloud.repository.TagCloudRepository
import org.springframework.stereotype.Service

@Service
class TagCloudService(private val tagCloudRepository: TagCloudRepository) {
    companion object {
        private val ALPHANUMERIC_ALPHABET =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    }

    fun getEntry(id: String): TagCloud? = tagCloudRepository.findById(id).orElse(null)

    fun createEntry(): TagCloud = tagCloudRepository.save(
        TagCloud(NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, ALPHANUMERIC_ALPHABET, 10))
    )

    fun deleteEntry(id: String): Boolean {
        return if (tagCloudRepository.existsById(id)) {
            tagCloudRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}