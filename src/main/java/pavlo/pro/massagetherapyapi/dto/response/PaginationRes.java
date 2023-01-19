package pavlo.pro.massagetherapyapi.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class PaginationRes<T> {
    List<T> items;
    long totalItems;
    int currentPage;
    int totalPages;

    public PaginationRes(
        List<T> items,
        long totalItems,
        int currentPage,
        int totalPages
    ) {
        this.items = items;
        this.totalItems = totalItems;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }
}
