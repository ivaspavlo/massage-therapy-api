package pavlo.pro.massagetherapyapi.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class RoleDto {
    @NotBlank
    private String name;
}
