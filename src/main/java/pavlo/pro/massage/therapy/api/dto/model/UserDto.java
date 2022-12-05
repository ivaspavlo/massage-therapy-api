package pavlo.pro.massage.therapy.api.dto.model;

import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pavlo.pro.massage.therapy.api.model.Article;
import pavlo.pro.massage.therapy.api.model.Booking;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String birth;
    private HashSet<RoleDto> roles;
    private HashSet<Booking> bookings;
    private HashSet<Article> articles;
}
