package com.k2.k2service.rest

import com.k2.k2service.domain.Tag
import com.k2.k2service.service.TagService
import com.k2.k2service.rest.errors.BadRequestAlertException

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Pageable
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid
import java.net.URI
import java.net.URISyntaxException

private const val ENTITY_NAME = "khipsterTag"
/**
 * REST controller for managing [com.k1.demo.domain.Tag].
 * paginate Post, Tag with infinite-scroll
 * paginate Blog with pagination
 */
@RestController
@RequestMapping("/api")
class TagResource(
    private val tagService: TagService
) {

    private val log = LoggerFactory.getLogger(javaClass)
    @Value("\${spring.application.name}")
    private var applicationName: String? = null

    /**
     * `POST  /tags` : Create a new tag.
     *
     * @param tag the tag to create.
     * @return the [ResponseEntity] with status `201 (Created)` and with body the new tag, or with status `400 (Bad Request)` if the tag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tags")
    fun createTag(@Valid @RequestBody tag: Tag): ResponseEntity<Tag> {
        log.debug("REST request to save Tag : $tag")
        if (tag.id != null) {
            throw BadRequestAlertException(
                "A new tag cannot already have an ID",
                ENTITY_NAME, "idexists"
            )
        }
        val result = tagService.save(tag)
        return ResponseEntity.created(URI("/api/tags/${result.id}"))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.id))
            .body(result)
    }

    /**
     * `PUT  /tags` : Updates an existing tag.
     *
     * @param tag the tag to update.
     * @return the [ResponseEntity] with status `200 (OK)` and with body the updated tag,
     * or with status `400 (Bad Request)` if the tag is not valid,
     * or with status `500 (Internal Server Error)` if the tag couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tags")
    fun updateTag(@Valid @RequestBody tag: Tag): ResponseEntity<Tag> {
        log.debug("REST request to update Tag : $tag")
        if (tag.id == null) {
            throw BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull")
        }
        val result = tagService.save(tag)
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName, false, ENTITY_NAME,
                     tag.id
                )
            )
            .body(result)
    }
    /**
     * `GET  /tags` : get all the tags.
     *
     * @param pageable the pagination information.

     * @return the [ResponseEntity] with status `200 (OK)` and the list of tags in body.
     */
    @GetMapping("/tags")    
    fun getAllTags(pageable: Pageable): ResponseEntity<List<Tag>> {
        log.debug("REST request to get a page of Tags")
        val page = tagService.findAll(pageable)
        val headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.content)
    }

    /**
     * `GET  /tags/:id` : get the "id" tag.
     *
     * @param id the id of the tag to retrieve.
     * @return the [ResponseEntity] with status `200 (OK)` and with body the tag, or with status `404 (Not Found)`.
     */
    @GetMapping("/tags/{id}")
    fun getTag(@PathVariable id: String): ResponseEntity<Tag> {
        log.debug("REST request to get Tag : $id")
        val tag = tagService.findOne(id)
        return ResponseUtil.wrapOrNotFound(tag)
    }
    /**
     *  `DELETE  /tags/:id` : delete the "id" tag.
     *
     * @param id the id of the tag to delete.
     * @return the [ResponseEntity] with status `204 (NO_CONTENT)`.
     */
    @DeleteMapping("/tags/{id}")
    fun deleteTag(@PathVariable id: String): ResponseEntity<Void> {
        log.debug("REST request to delete Tag : $id")

        tagService.delete(id)
            return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build()
    }
}
