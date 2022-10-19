package pavlo.pro.massage_therapy_api.rest;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pavlo.pro.massage_therapy_api.model.BlogArticleDTO;
import pavlo.pro.massage_therapy_api.service.BlogArticleService;


@RestController
@RequestMapping(value = "/api/blogArticles", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogArticleResource {

    private final BlogArticleService blogArticleService;

    public BlogArticleResource(final BlogArticleService blogArticleService) {
        this.blogArticleService = blogArticleService;
    }

    @GetMapping
    public ResponseEntity<List<BlogArticleDTO>> getAllBlogArticles() {
        return ResponseEntity.ok(blogArticleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogArticleDTO> getBlogArticle(@PathVariable final Long id) {
        return ResponseEntity.ok(blogArticleService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createBlogArticle(
            @RequestBody @Valid final BlogArticleDTO blogArticleDTO) {
        return new ResponseEntity<>(blogArticleService.create(blogArticleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBlogArticle(@PathVariable final Long id,
            @RequestBody @Valid final BlogArticleDTO blogArticleDTO) {
        blogArticleService.update(id, blogArticleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogArticle(@PathVariable final Long id) {
        blogArticleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
