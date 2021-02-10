package com.k2.k2service.repository

import com.k2.k2service.domain.Tag
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Spring Data MongoDB repository for the [Tag] entity.
 */
@Suppress("unused")
@Repository
interface TagRepository : MongoRepository<Tag, String> {
}
