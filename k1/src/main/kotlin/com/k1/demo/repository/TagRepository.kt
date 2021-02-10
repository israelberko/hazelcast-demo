package com.k1.demo.repository

import com.k1.demo.domain.Tag
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Spring Data MongoDB repository for the [Tag] entity.
 */
@Suppress("unused")
@Repository
interface TagRepository : MongoRepository<Tag, String> {
}
