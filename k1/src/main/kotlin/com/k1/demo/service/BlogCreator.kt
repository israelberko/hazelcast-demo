package com.k1.demo.service

import com.k1.demo.domain.Blog

interface BlogCreator {

    fun create(): Blog
    fun createBlogs(limit:Int = 1000): List<Blog>
}