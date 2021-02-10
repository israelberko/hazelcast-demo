package com.k1.demo.service.impl

import com.k1.demo.service.BlogService
import com.k1.demo.domain.Blog
import com.k1.demo.repository.BlogRepository
import org.slf4j.LoggerFactory

import org.springframework.stereotype.Service
import java.util.*

/**
 * Service Implementation for managing [Blog].
 */
@Service
class BlogServiceImpl(val blogRepository: BlogRepository) : BlogService {

    private val log = LoggerFactory.getLogger(javaClass)


    override fun save(blog: Blog): Blog {
        log.debug("Request to save Blog : $blog")
        return blogRepository.save(blog)
    }

    override fun findAll(): List<Blog> {
        log.debug("Request to get all Blogs")
        return listOf(Blog("", "", ""))
    }

    override fun findOne(id: String): Optional<Blog> {
        log.debug("Request to get Blog : $id")
        return blogRepository.findById(id);
    }

    override fun findByName(id: String): Optional<Blog> {
        log.debug("Request to get Blog : $id")
        return Optional.of(blogRepository.findByName(id));
    }

    override fun delete(id: String) {
        log.debug("Request to delete Blog : $id")

        blogRepository.deleteById(id)
    }

}
