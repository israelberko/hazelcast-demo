package com.k1.demo.service.impl

import com.k1.demo.domain.Blog
import com.k1.demo.service.BlogCreator
import org.jeasy.random.EasyRandom
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.stream.IntStream

@Service
class BlogCreatorImpl : BlogCreator {
    private val log = LoggerFactory.getLogger(javaClass)
    var generator: EasyRandom = EasyRandom()

    override fun create(): Blog {
        val blogB: Blog = generator.nextObject(Blog::class.java)
        log.info("new Blog created {}", blogB)
        return blogB
    }

    override fun createBlogs(limit: Int): List<Blog> {
        val blogs = mutableListOf<Blog>()
        var blogB: Blog
        IntStream.range(1, limit).forEach {
            blogB = generator.nextObject(Blog::class.java)
            log.info("new Blog created {}", blogB)
            blogs.add(blogB)
        }

        return blogs
    }
}