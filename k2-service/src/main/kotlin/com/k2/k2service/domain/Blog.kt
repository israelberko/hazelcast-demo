package com.k2.k2service.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import javax.validation.constraints.*

import java.io.Serializable

/**
 * A Blog.
 */
@Document(collection = "blog")
data class Blog(
    @Id
    var id: String? = null,
    @get: NotNull
    @get: Size(min = 3)
    @Field("name")
    var name: String? = null,

    @get: NotNull
    @get: Size(min = 2)
    @Field("handle")
    var handle: String? = null

    // jhipster-needle-entity-add-field - JHipster will add fields here
) : Serializable {
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Blog) return false

        return id != null && other.id != null && id == other.id
    }

    override fun hashCode() = 31

    override fun toString() = "Blog{" +
        "id=$id" +
        ", name='$name'" +
        ", handle='$handle'" +
        "}"


    companion object {
        private const val serialVersionUID = 1L
    }
}
