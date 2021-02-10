package com.k2.k2service.service.impl

import com.k2.k2service.service.BlogService
import com.k2.k2service.domain.Blog
import com.k2.k2service.domain.Post
import com.k2.k2service.repository.BlogRepository
import com.k2.k2service.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.Optional

/**
 * Service Implementation for managing [Blog].
 */
@Service
class BlogServiceImpl(
        private val blogRepository: BlogRepository,
        private val postService: PostService
) : BlogService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun save(blog: Blog): Blog {
        log.debug("Request to save Blog : $blog")
        return blogRepository.save(blog)
    }

    override fun findAll(pageable: Pageable): Page<Blog> {
        log.debug("Request to get all Blogs")
        return blogRepository.findAll(pageable)
    }


    @Cacheable("blogs")
    override fun findOne(id: String): Optional<Blog> {
        log.debug("Request to get Blog : $id")
        Thread.sleep(3000)
        log.debug("Request after sleep")
        return blogRepository.findById(id)
    }

    override fun delete(id: String): Unit {
        log.debug("Request to delete Blog : $id")

        blogRepository.deleteById(id)
    }

    override fun getNull(id: String): Blog? {
        log.debug("Request to getNull")
        val postByID = postService.findOne(id)
        log.info("Test if internal function calling to external is logged:: ${postByID.let { "Default From let function" }}")
        return null
    }

    override fun throwOne(id: String, second: String, third: Int): Blog {
        log.debug("Request to throw Blog : $id")

        val blog = blogRepository.findAll()[0]
        log.info("Blog ID of the first blog post is:: ${blog.id}")

        val postByID = postService.findOne(blog.id.let { "11111" })
        log.info("Blog ID of the first blog post is:: ${postByID?.orElse(Post())}")

        val nullParam = getNull(blog.id.let { "22222" })
        log.info("Printing NULL parameter:: $nullParam")
        throw RuntimeException("Test Exception Throwing without a catch", Throwable("IsraelB Wanted it!"))

//        return blogRepository.findAll()[0]
    }
}
