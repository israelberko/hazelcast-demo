package com.k2.k2service.repository

import com.k2.k2service.domain.Blog
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Spring Data MongoDB repository for the [Blog] entity.
 */
@Suppress("unused")
@Repository
interface BlogRepository : MongoRepository<Blog, String> {
}
