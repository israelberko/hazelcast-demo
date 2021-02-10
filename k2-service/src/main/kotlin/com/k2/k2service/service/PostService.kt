package com.k2.k2service.service
import com.k2.k2service.domain.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import java.util.Optional

/**
 * Service Interface for managing [Post].
 */
interface PostService {

    /**
     * Save a post.
     *
     * @param post the entity to save.
     * @return the persisted entity.
     */
    fun save(post: Post): Post

    /**
     * Get all the posts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    fun findAll(pageable: Pageable): Page<Post>

    /**
     * Get all the posts with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    fun findAllWithEagerRelationships(pageable: Pageable): Page<Post>
    /**
     * Get the "id" post.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    fun findOne(id: String): Optional<Post>

    /**
     * Delete the "id" post.
     *
     * @param id the id of the entity.
     */
    fun delete(id: String)
    
    
    
}
