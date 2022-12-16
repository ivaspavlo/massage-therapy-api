package pavlo.pro.massagetherapyapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document("users")
public class User {

    @Id
    private String id;

    @NotBlank
    @Email
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String email;

    @NotBlank
    @Size(max = 30)
    private String firstName;

    @NotBlank
    @Size(max = 30)
    private String lastName;

    @NotBlank
    @Size(max = 50)
    private String password;

    @DBRef
    private HashSet<Role> roles = new HashSet<>();

}
