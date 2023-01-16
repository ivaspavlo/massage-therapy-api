package pavlo.pro.massagetherapyapi.payload.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserReq {
    @NotBlank
    public String id;
    public String firstName;
    public String lastName;
    public String phone;
}
