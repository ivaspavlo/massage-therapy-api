package pavlo.pro.massage_therapy_api.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class BlogArticle {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate created;

    @Column(nullable = false)
    private Integer hearts;

    @Column(nullable = false)
    private String titleImg;

    @Column(nullable = false)
    private String intro;

    @Column(nullable = false)
    private String watchedTimes;

    @Column(nullable = false)
    private String author;

    @Column
    private String authorImg;

    @OneToMany(mappedBy = "blogArticle")
    private Set<User> blogArticleUsers;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(final LocalDate created) {
        this.created = created;
    }

    public Integer getHearts() {
        return hearts;
    }

    public void setHearts(final Integer hearts) {
        this.hearts = hearts;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(final String titleImg) {
        this.titleImg = titleImg;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(final String intro) {
        this.intro = intro;
    }

    public String getWatchedTimes() {
        return watchedTimes;
    }

    public void setWatchedTimes(final String watchedTimes) {
        this.watchedTimes = watchedTimes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getAuthorImg() {
        return authorImg;
    }

    public void setAuthorImg(final String authorImg) {
        this.authorImg = authorImg;
    }

    public Set<User> getBlogArticleUsers() {
        return blogArticleUsers;
    }

    public void setBlogArticleUsers(final Set<User> blogArticleUsers) {
        this.blogArticleUsers = blogArticleUsers;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
