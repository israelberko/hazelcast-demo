package com.k1.demo.service
import com.k1.demo.domain.Blog

import java.util.Optional

/**
 * Service Interface for managing [Blog].
 */
interface BlogService {

    /**
     * Save a blog.
     *
     * @param blog the entity to save.
     * @return the persisted entity.
     */
    fun save(blog: Blog): Blog

    /**
     * Get all the blogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    fun findAll(): List<Blog>

    /**
     * Get the "id" blog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    fun findOne(id: String): Optional<Blog>

    /**
     * Delete the "id" blog.
     *
     * @param id the id of the entity.
     */
    fun delete(id: String)

    fun findByName(id: String): Optional<Blog>

    
    
    
}
