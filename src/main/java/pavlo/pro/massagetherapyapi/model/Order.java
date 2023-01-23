package pavlo.pro.massagetherapyapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;

public class Order {
    @Id
    private String id;

    @NotBlank
    @DBRef
    String massageId;

    @NotBlank
    @DBRef
    String userId;

    @DBRef
    HashSet<BookingSlot> items;
}
