package com.k1.demo.repository

import com.k1.demo.domain.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

/**
 * Spring Data MongoDB repository for the [Post] entity.
 */
@Repository
interface PostRepository : MongoRepository<Post, String> {

    @Query("{}")
    fun findAllWithEagerRelationships(pageable: Pageable): Page<Post>

    @Query("{}")
    fun findAllWithEagerRelationships(): MutableList<Post>

    @Query("{'id': ?0}")
    fun findOneWithEagerRelationships(id: String): Optional<Post>
}
