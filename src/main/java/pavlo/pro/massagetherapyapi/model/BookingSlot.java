package pavlo.pro.massagetherapyapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document("massageBookingSlot")
public class BookingSlot {
    @Id
    private String id;

    @NotBlank
    String start;

    @NotBlank
    String end;

    @DBRef
    String massageId;
}
