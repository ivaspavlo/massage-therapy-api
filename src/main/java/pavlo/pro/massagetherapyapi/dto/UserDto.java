package pavlo.pro.massagetherapyapi.dto;

import lombok.Data;
import pavlo.pro.massagetherapyapi.model.ERole;
import pavlo.pro.massagetherapyapi.model.Role;
import java.util.HashSet;

@Data
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean isAdmin;
    public Boolean getIsAdmin() {
        return this.isAdmin;
    }
    public void setIsAdmin(HashSet<Role> roles) {
        // TODO: to test
        this.isAdmin = roles.stream().anyMatch(
            role -> role.getName().equals(ERole.ROLE_ADMIN)
        );
    }
}
