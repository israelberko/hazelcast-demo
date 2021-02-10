package com.k2.k2service.service.impl

import com.k2.k2service.service.TagService
import com.k2.k2service.domain.Tag
import com.k2.k2service.repository.TagRepository
import org.slf4j.LoggerFactory

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Optional

/**
 * Service Implementation for managing [Tag].
 */
@Service
class TagServiceImpl(
        private val tagRepository: TagRepository
) : TagService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun save(tag: Tag): Tag {
        log.debug("Request to save Tag : $tag")
        return tagRepository.save(tag)
    }

    override fun findAll(pageable: Pageable): Page<Tag> {
        log.debug("Request to get all Tags")
        return tagRepository.findAll(pageable)
    }


    override fun findOne(id: String): Optional<Tag> {
        log.debug("Request to get Tag : $id")
        return tagRepository.findById(id)
    }

    override fun delete(id: String): Unit {
        log.debug("Request to delete Tag : $id")

        tagRepository.deleteById(id)
    }
}
