package pavlo.pro.massage.therapy.api.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pavlo.pro.massage.therapy.api.model.ERole;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto {
    private String id;
    private ERole name;
}
