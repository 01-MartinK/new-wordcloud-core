package com.mk.wordcloud.repository

import com.mk.wordcloud.model.TagCloud
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface TagCloudRepository: JpaRepository<TagCloud, Long>