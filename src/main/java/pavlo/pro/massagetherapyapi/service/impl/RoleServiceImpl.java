package pavlo.pro.massagetherapyapi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pavlo.pro.massagetherapyapi.config.PropertiesConfig;
import pavlo.pro.massagetherapyapi.dto.RoleDto;
import pavlo.pro.massagetherapyapi.exception.AppException;
import pavlo.pro.massagetherapyapi.exception.EntityType;
import pavlo.pro.massagetherapyapi.exception.ExceptionType;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.repository.RoleRepository;
import pavlo.pro.massagetherapyapi.service.interfaces.RoleService;

import static pavlo.pro.massagetherapyapi.exception.EntityType.ROLE;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PropertiesConfig propertiesConfig;

    private String roleNamePrefix = "role.name.prefix";

    public Role createRole(RoleDto newRole) throws RuntimeException {
        String roleName = propertiesConfig.getConfigValue(roleNamePrefix) + newRole.getName().toUpperCase();
        Role existingRole = roleRepository.findByName(roleName);
        if (existingRole != null) {
            throw exception(ROLE, ExceptionType.DUPLICATE_ENTITY, newRole.getName());
        }
        Role roleToCreate = new Role().setName(roleName);
        try {
            return roleRepository.insert(roleToCreate);
        } catch(Exception exception) {
            throw exception(ROLE, ExceptionType.ENTITY_EXCEPTION);
        }
    }

    public List<Role> getAllRoles() throws RuntimeException {
        try {
            return roleRepository.findAll();
        } catch(Exception exception) {
            throw exception(ROLE, ExceptionType.ENTITY_EXCEPTION);
        }
    }

    public Role findRoleByName(String roleName) throws RuntimeException {
        try {
            return roleRepository.findByName(roleName);
        } catch(Exception exception) {
            throw exception(ROLE, ExceptionType.ENTITY_NOT_FOUND, roleName);
        }
    }

    // TODO: move to utils
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return AppException.throwException(entityType, exceptionType, args);
    }

}
