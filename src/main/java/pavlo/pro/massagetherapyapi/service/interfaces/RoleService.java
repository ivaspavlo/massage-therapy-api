package pavlo.pro.massagetherapyapi.service.interfaces;

import pavlo.pro.massagetherapyapi.dto.RoleDto;
import pavlo.pro.massagetherapyapi.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRoles();
    public Role createRole(RoleDto roleDto);
    public Role findRoleByName(String name);
}
