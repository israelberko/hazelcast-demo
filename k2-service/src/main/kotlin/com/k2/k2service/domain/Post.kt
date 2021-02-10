package com.k2.k2service.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.DBRef
import javax.validation.constraints.*

import java.io.Serializable
import java.time.Instant

/**
 * A Post.
 */
@Document(collection = "post")
data class Post(
        @Id
    var id: String? = null,
        @get: NotNull
    @Field("title")
    var title: String? = null,


        @Field("content")
    var content: String? = null,

        @get: NotNull
    @Field("date")
    var date: Instant? = null,

        @DBRef
    @Field("blog")
    @JsonIgnoreProperties(value = ["posts"], allowSetters = true)
    var blog: Blog? = null,

        @DBRef
    @Field("tags")
    var tags: MutableSet<Tag> = mutableSetOf()

    // jhipster-needle-entity-add-field - JHipster will add fields here
) : Serializable {

    fun addTag(tag: Tag): Post {
        this.tags.add(tag)
        return this
    }

    fun removeTag(tag: Tag): Post {
        this.tags.remove(tag)
        return this
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Post) return false

        return id != null && other.id != null && id == other.id
    }

    override fun hashCode() = 31

    override fun toString() = "Post{" +
        "id=$id" +
        ", title='$title'" +
        ", content='$content'" +
        ", date='$date'" +
        "}"


    companion object {
        private const val serialVersionUID = 1L
    }
}
