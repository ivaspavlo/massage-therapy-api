package pavlo.pro.massage_therapy_api.model;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class BlogArticleDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    @Size(max = 255)
    private String content;

    @NotNull
    private LocalDate created;

    @NotNull
    private Integer hearts;

    @NotNull
    @Size(max = 255)
    private String titleImg;

    @NotNull
    @Size(max = 255)
    private String intro;

    @NotNull
    @Size(max = 255)
    private String watchedTimes;

    @NotNull
    @Size(max = 255)
    private String author;

    @Size(max = 255)
    private String authorImg;

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

}
