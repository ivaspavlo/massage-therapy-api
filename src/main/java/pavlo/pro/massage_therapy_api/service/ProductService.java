package pavlo.pro.massage_therapy_api.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pavlo.pro.massage_therapy_api.domain.Booking;
import pavlo.pro.massage_therapy_api.domain.Product;
import pavlo.pro.massage_therapy_api.model.ProductDTO;
import pavlo.pro.massage_therapy_api.repos.BookingRepository;
import pavlo.pro.massage_therapy_api.repos.ProductRepository;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BookingRepository bookingRepository;

    public ProductService(final ProductRepository productRepository,
            final BookingRepository bookingRepository) {
        this.productRepository = productRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll(Sort.by("id"))
                .stream()
                .map(product -> mapToDTO(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    public ProductDTO get(final Long id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final Long id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setSubtitle(product.getSubtitle());
        productDTO.setText(product.getText());
        productDTO.setPrice(product.getPrice());
        productDTO.setBooking(product.getBooking() == null ? null : product.getBooking().getId());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setTitle(productDTO.getTitle());
        product.setSubtitle(productDTO.getSubtitle());
        product.setText(productDTO.getText());
        product.setPrice(productDTO.getPrice());
        final Booking booking = productDTO.getBooking() == null ? null : bookingRepository.findById(productDTO.getBooking())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "booking not found"));
        product.setBooking(booking);
        return product;
    }

}
