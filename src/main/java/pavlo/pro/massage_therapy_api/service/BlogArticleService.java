package pavlo.pro.massage_therapy_api.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pavlo.pro.massage_therapy_api.domain.BlogArticle;
import pavlo.pro.massage_therapy_api.model.BlogArticleDTO;
import pavlo.pro.massage_therapy_api.repos.BlogArticleRepository;


@Service
public class BlogArticleService {

    private final BlogArticleRepository blogArticleRepository;

    public BlogArticleService(final BlogArticleRepository blogArticleRepository) {
        this.blogArticleRepository = blogArticleRepository;
    }

    public List<BlogArticleDTO> findAll() {
        return blogArticleRepository.findAll(Sort.by("id"))
                .stream()
                .map(blogArticle -> mapToDTO(blogArticle, new BlogArticleDTO()))
                .collect(Collectors.toList());
    }

    public BlogArticleDTO get(final Long id) {
        return blogArticleRepository.findById(id)
                .map(blogArticle -> mapToDTO(blogArticle, new BlogArticleDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final BlogArticleDTO blogArticleDTO) {
        final BlogArticle blogArticle = new BlogArticle();
        mapToEntity(blogArticleDTO, blogArticle);
        return blogArticleRepository.save(blogArticle).getId();
    }

    public void update(final Long id, final BlogArticleDTO blogArticleDTO) {
        final BlogArticle blogArticle = blogArticleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(blogArticleDTO, blogArticle);
        blogArticleRepository.save(blogArticle);
    }

    public void delete(final Long id) {
        blogArticleRepository.deleteById(id);
    }

    private BlogArticleDTO mapToDTO(final BlogArticle blogArticle,
            final BlogArticleDTO blogArticleDTO) {
        blogArticleDTO.setId(blogArticle.getId());
        blogArticleDTO.setTitle(blogArticle.getTitle());
        blogArticleDTO.setContent(blogArticle.getContent());
        blogArticleDTO.setCreated(blogArticle.getCreated());
        blogArticleDTO.setHearts(blogArticle.getHearts());
        blogArticleDTO.setTitleImg(blogArticle.getTitleImg());
        blogArticleDTO.setIntro(blogArticle.getIntro());
        blogArticleDTO.setWatchedTimes(blogArticle.getWatchedTimes());
        blogArticleDTO.setAuthor(blogArticle.getAuthor());
        blogArticleDTO.setAuthorImg(blogArticle.getAuthorImg());
        return blogArticleDTO;
    }

    private BlogArticle mapToEntity(final BlogArticleDTO blogArticleDTO,
            final BlogArticle blogArticle) {
        blogArticle.setTitle(blogArticleDTO.getTitle());
        blogArticle.setContent(blogArticleDTO.getContent());
        blogArticle.setCreated(blogArticleDTO.getCreated());
        blogArticle.setHearts(blogArticleDTO.getHearts());
        blogArticle.setTitleImg(blogArticleDTO.getTitleImg());
        blogArticle.setIntro(blogArticleDTO.getIntro());
        blogArticle.setWatchedTimes(blogArticleDTO.getWatchedTimes());
        blogArticle.setAuthor(blogArticleDTO.getAuthor());
        blogArticle.setAuthorImg(blogArticleDTO.getAuthorImg());
        return blogArticle;
    }

}
