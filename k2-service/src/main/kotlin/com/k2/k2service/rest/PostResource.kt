package com.k2.k2service.rest

import com.k2.k2service.domain.Post
import com.k2.k2service.service.PostService
import com.k2.k2service.rest.errors.BadRequestAlertException

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid
import java.net.URI
import java.net.URISyntaxException

private const val ENTITY_NAME = "khipsterPost"
/**
 * REST controller for managing [com.k1.demo.domain.Post].
 * paginate Post, Tag with infinite-scroll
 * paginate Blog with pagination
 */
@RestController
@RequestMapping("/api")
class PostResource(
    private val postService: PostService
) {

    private val log = LoggerFactory.getLogger(javaClass)
    @Value("\${spring.application.name}")
    private var applicationName: String? = null

    /**
     * `POST  /posts` : Create a new post.
     *
     * @param post the post to create.
     * @return the [ResponseEntity] with status `201 (Created)` and with body the new post, or with status `400 (Bad Request)` if the post has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/posts")
    fun createPost(@Valid @RequestBody post: Post): ResponseEntity<Post> {
        log.debug("REST request to save Post : $post")
        if (post.id != null) {
            throw BadRequestAlertException(
                "A new post cannot already have an ID",
                ENTITY_NAME, "idexists"
            )
        }
        val result = postService.save(post)
        return ResponseEntity.created(URI("/api/posts/${result.id}"))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.id))
            .body(result)
    }

    /**
     * `PUT  /posts` : Updates an existing post.
     *
     * @param post the post to update.
     * @return the [ResponseEntity] with status `200 (OK)` and with body the updated post,
     * or with status `400 (Bad Request)` if the post is not valid,
     * or with status `500 (Internal Server Error)` if the post couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/posts")
    fun updatePost(@Valid @RequestBody post: Post): ResponseEntity<Post> {
        log.debug("REST request to update Post : $post")
        if (post.id == null) {
            throw BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull")
        }
        val result = postService.save(post)
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName, false, ENTITY_NAME,
                     post.id
                )
            )
            .body(result)
    }
    /**
     * `GET  /posts` : get all the posts.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the [ResponseEntity] with status `200 (OK)` and the list of posts in body.
     */
    @GetMapping("/posts")    
    fun getAllPosts(pageable: Pageable, @RequestParam(required = false, defaultValue = "false") eagerload: Boolean): ResponseEntity<List<Post>> {
        log.debug("REST request to get a page of Posts")
        val page: Page<Post> = if (eagerload) {
            postService.findAllWithEagerRelationships(pageable)
        } else {
            postService.findAll(pageable)
        }
        val headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page)
        return ResponseEntity.ok().headers(headers).body(page.content)
    }

    /**
     * `GET  /posts/:id` : get the "id" post.
     *
     * @param id the id of the post to retrieve.
     * @return the [ResponseEntity] with status `200 (OK)` and with body the post, or with status `404 (Not Found)`.
     */
    @GetMapping("/posts/{id}")
    fun getPost(@PathVariable id: String): ResponseEntity<Post> {
        log.debug("REST request to get Post : $id")
        val post = postService.findOne(id)
        return ResponseUtil.wrapOrNotFound(post)
    }
    /**
     *  `DELETE  /posts/:id` : delete the "id" post.
     *
     * @param id the id of the post to delete.
     * @return the [ResponseEntity] with status `204 (NO_CONTENT)`.
     */
    @DeleteMapping("/posts/{id}")
    fun deletePost(@PathVariable id: String): ResponseEntity<Void> {
        log.debug("REST request to delete Post : $id")

        postService.delete(id)
            return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build()
    }
}
