package pavlo.pro.massagetherapyapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document("massageOrders")
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
