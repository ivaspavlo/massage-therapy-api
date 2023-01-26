package pavlo.pro.massagetherapyapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


//https://stackoverflow.com/questions/53870484/ideal-way-to-pass-date-from-angular-datepicker-to-java-backend
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document("massageBookingSlot")
public class MassageBookingSlot {
    @Id
    private String id;

    @NotBlank
    LocalDateTime start;

    @NotBlank
    LocalDateTime end;

    String massageId;
}
