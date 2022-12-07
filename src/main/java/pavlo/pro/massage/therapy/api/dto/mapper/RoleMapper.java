package pavlo.pro.massage.therapy.api.dto.mapper;

import pavlo.pro.massage.therapy.api.dto.model.RoleDto;
import pavlo.pro.massage.therapy.api.model.Role;

public class RoleMapper {
    public static RoleDto toRoleDto(Role role) {
        return new RoleDto()
                .setId(role.getId())
                .setName(role.getName());
    }
}
