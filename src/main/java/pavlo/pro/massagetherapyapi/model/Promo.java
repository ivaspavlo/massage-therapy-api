package pavlo.pro.massagetherapyapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document("promos")
public class Promo {
    @Id
    private String id;

    @NotBlank
    @Indexed(unique = true)
    private String code;

    public Promo(String generatedCode) {
        this.code = generatedCode;
    }
}
