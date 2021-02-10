package com.k2.k2service.service
import com.k2.k2service.domain.Blog
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

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
    fun findAll(pageable: Pageable): Page<Blog>

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


    fun throwOne(id: String, second:String, third: Int): Blog

    fun getNull(id: String): Blog?
    
    
}
