package pavlo.pro.massagetherapyapi.dto;

import lombok.Data;
import pavlo.pro.massagetherapyapi.model.enums.ERole;
import pavlo.pro.massagetherapyapi.model.Role;
import java.util.HashSet;

@Data
public class UserDto {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean isAdmin;
    public Boolean getIsAdmin() {
        return this.isAdmin;
    }
    public void setIsAdmin(HashSet<Role> roles) {
        this.isAdmin = roles.stream().anyMatch(
            role -> role.getName().equals(ERole.ROLE_ADMIN.toString())
        );
    }
}
