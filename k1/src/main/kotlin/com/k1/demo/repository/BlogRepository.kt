package com.k1.demo.repository

import com.k1.demo.domain.Blog
import org.springframework.data.hazelcast.repository.HazelcastRepository
import org.springframework.stereotype.Repository

/**
 * Spring Data MongoDB repository for the [Blog] entity.
 */
@Suppress("unused")
@Repository
interface BlogRepository : HazelcastRepository<Blog, String> {
    fun findByName(name: String) : Blog
}
