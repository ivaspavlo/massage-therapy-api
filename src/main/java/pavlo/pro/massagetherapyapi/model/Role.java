package pavlo.pro.massagetherapyapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pavlo.pro.massagetherapyapi.model.enums.ERole;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document("roles")
public class Role {
    @Id
    private String id;
    private String name;
}
