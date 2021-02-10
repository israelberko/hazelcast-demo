package com.k1.demo.rest

import com.k1.demo.domain.Blog
import com.k1.demo.rest.errors.BadRequestAlertException
//import com.k1.demo.domain.Clog
import com.k1.demo.service.BlogCreator
import com.k1.demo.service.BlogService
import com.k1.demo.service.impl.BlogCacheImpl

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import java.net.URI
import java.net.URISyntaxException
import java.util.*
import javax.validation.Valid

private const val ENTITY_NAME = "khipsterBlog"

/**
 * REST controller for managing [com.k1.demo.domain.Blog].
 * paginate Post, Tag with infinite-scroll
 * paginate Blog with pagination
 */
@RestController
@RequestMapping("/api")
class BlogResource(
        private val blogService: BlogService,
        private val blogCreator: BlogCreator
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Value("\${spring.application.name}")
    private var applicationName: String? = null

    /**
     * `POST  /blogs` : Create a new blog.
     *
     * @param blog the blog to create.
     * @return the [ResponseEntity] with status `201 (Created)` and with body the new blog, or with status `400 (Bad Request)` if the blog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/blogs")
    fun createBlog(@Valid @RequestBody blog: Blog): ResponseEntity<Blog> {
        log.debug("REST request to save Blog : $blog")

        val result = blogService.save(blog)
        return ResponseEntity.created(URI("/api/blogs/${result.id}"))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.id))
                .body(result)
    }

    /**
     * `PUT  /blogs` : Updates an existing blog.
     *
     * @param blog the blog to update.
     * @return the [ResponseEntity] with status `200 (OK)` and with body the updated blog,
     * or with status `400 (Bad Request)` if the blog is not valid,
     * or with status `500 (Internal Server Error)` if the blog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PutMapping("/blogs")
//    fun updateBlog(@Valid @RequestBody blog: Blog): ResponseEntity<Blog> {
//        log.debug("REST request to update Blog : $blog")
//        if (blog.id == null) {
//            throw RuntimeException("Invalid id")
//        }
//        val result = blogService.save(blog)
//        return ResponseEntity.ok()
//            .headers(
//                HeaderUtil.createEntityUpdateAlert(
//                    applicationName, false, ENTITY_NAME,
//                     blog.id
//                )
//            )
//            .body(result)
//    }
    /**
     * `GET  /blogs` : get all the blogs.
     *
     * @param pageable the pagination information.

     * @return the [ResponseEntity] with status `200 (OK)` and the list of blogs in body.
     */
    @GetMapping("/blogs")
    fun getAllBlogs(): ResponseEntity<List<Blog>> {
        log.debug("REST request to get a page of Blogs")
        val blogs = blogService.findAll()
        return ResponseEntity.ok().body(blogs)
    }


    @GetMapping("/blogs/init")
    fun initAllBlogs(): ResponseEntity<List<Blog>> {
        log.debug("REST request to get a page of Blogs")
        val blogs = blogCreator.createBlogs(100)
        blogs.forEach { blogService.save(it) }
        return ResponseEntity.ok().body(blogs)
    }

    /**
     * `GET  /blogs/:id` : get the "id" blog.
     *
     * @param id the id of the blog to retrieve.
     * @return the [ResponseEntity] with status `200 (OK)` and with body the blog, or with status `404 (Not Found)`.
     */
    @GetMapping("/blogs/{id}")
    fun getBlog(@PathVariable id: String): ResponseEntity<Blog> {
        log.debug("REST request to get Blog : $id")
        val blog = blogService.findOne(id)
        return ResponseUtil.wrapOrNotFound(blog)
    }

    /**
     * `GET  /blogs/:id` : get the "id" blog.
     *
     * @param id the id of the blog to retrieve.
     * @return the [ResponseEntity] with status `200 (OK)` and with body the blog, or with status `404 (Not Found)`.
     */
    @GetMapping("/blogs/name/{name}")
    fun getBlogByName(@PathVariable name: String): ResponseEntity<Blog> {
        log.debug("REST request to get Blog By Name: $name")
        val blog = blogService.findByName(name)
        return ResponseUtil.wrapOrNotFound(blog)
    }

    /**
     *  `DELETE  /blogs/:id` : delete the "id" blog.
     *
     * @param id the id of the blog to delete.
     * @return the [ResponseEntity] with status `204 (NO_CONTENT)`.
     */
    @DeleteMapping("/blogs/{id}")
    fun deleteBlog(@PathVariable id: String): ResponseEntity<Void> {
        log.debug("REST request to delete Blog : $id")

        blogService.delete(id)
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build()
    }
}
