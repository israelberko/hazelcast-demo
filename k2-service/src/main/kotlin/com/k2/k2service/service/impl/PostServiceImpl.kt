package com.k2.k2service.service.impl

import com.k2.k2service.service.PostService
import com.k2.k2service.domain.Post
import com.k2.k2service.repository.PostRepository
import org.slf4j.LoggerFactory

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Optional

/**
 * Service Implementation for managing [Post].
 */
@Service
class PostServiceImpl(
        private val postRepository: PostRepository
) : PostService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun save(post: Post): Post {
        log.debug("Request to save Post : $post")
        return postRepository.save(post)
    }

    override fun findAll(pageable: Pageable): Page<Post> {
        log.debug("Request to get all Posts")
        return postRepository.findAll(pageable)
    }


    override fun findAllWithEagerRelationships(pageable: Pageable) =
        postRepository.findAllWithEagerRelationships(pageable)


    override fun findOne(id: String): Optional<Post> {
        log.debug("Request to get Post : $id")
        return postRepository.findOneWithEagerRelationships(id)
    }

    override fun delete(id: String): Unit {
        log.debug("Request to delete Post : $id")

        postRepository.deleteById(id)
    }
}
