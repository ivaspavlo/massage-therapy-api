package pavlo.pro.massage_therapy_api.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ProductDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    @Size(max = 255)
    private String subtitle;

    @NotNull
    @Size(max = 255)
    private String text;

    @NotNull
    private Integer price;

    @NotNull
    private Long booking;

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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(final String subtitle) {
        this.subtitle = subtitle;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(final Integer price) {
        this.price = price;
    }

    public Long getBooking() {
        return booking;
    }

    public void setBooking(final Long booking) {
        this.booking = booking;
    }

}
