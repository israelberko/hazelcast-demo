package com.k2.k2service.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.DBRef
import javax.validation.constraints.*

import java.io.Serializable

/**
 * A Tag.
 */
@Document(collection = "tag")
data class Tag(
    @Id
    var id: String? = null,
    @get: NotNull
    @get: Size(min = 2)
    @Field("name")
    var name: String? = null,

    @DBRef
    @Field("entries")
    @JsonIgnore
    var entries: MutableSet<Post> = mutableSetOf()

    // jhipster-needle-entity-add-field - JHipster will add fields here
) : Serializable {

    fun addEntry(post: Post): Tag {
        this.entries.add(post)
        post.tags.add(this)
        return this
    }

    fun removeEntry(post: Post): Tag {
        this.entries.remove(post)
        post.tags.remove(this)
        return this
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Tag) return false

        return id != null && other.id != null && id == other.id
    }

    override fun hashCode() = 31

    override fun toString() = "Tag{" +
        "id=$id" +
        ", name='$name'" +
        "}"


    companion object {
        private const val serialVersionUID = 1L
    }
}
