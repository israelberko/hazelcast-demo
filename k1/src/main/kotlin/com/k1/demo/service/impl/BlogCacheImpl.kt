package com.k1.demo.service.impl

import com.k1.demo.domain.Blog
import com.k1.demo.repository.BlogRepository
import org.springframework.stereotype.Service

@Service
class BlogCacheImpl(private val blogRepository: BlogRepository) {

    fun storeBlog(blog: Blog): Blog {

        return blogRepository.save(blog)

//        return blog
    }


}