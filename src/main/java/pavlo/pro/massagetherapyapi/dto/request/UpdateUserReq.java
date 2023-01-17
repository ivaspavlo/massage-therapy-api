package pavlo.pro.massagetherapyapi.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserReq {
    public String firstName;
    public String lastName;
    public String phone;
}
