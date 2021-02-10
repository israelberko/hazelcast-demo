package com.k1.demo.service
import com.k1.demo.domain.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import java.util.Optional

/**
 * Service Interface for managing [Tag].
 */
interface TagService {

    /**
     * Save a tag.
     *
     * @param tag the entity to save.
     * @return the persisted entity.
     */
    fun save(tag: Tag): Tag

    /**
     * Get all the tags.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    fun findAll(pageable: Pageable): Page<Tag>

    /**
     * Get the "id" tag.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    fun findOne(id: String): Optional<Tag>

    /**
     * Delete the "id" tag.
     *
     * @param id the id of the entity.
     */
    fun delete(id: String)
    
    
    
}
