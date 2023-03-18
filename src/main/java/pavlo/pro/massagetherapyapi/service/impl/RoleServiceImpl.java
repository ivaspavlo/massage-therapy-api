package pavlo.pro.massagetherapyapi.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pavlo.pro.massagetherapyapi.config.PropertiesConfig;
import pavlo.pro.massagetherapyapi.dto.RoleDto;
import pavlo.pro.massagetherapyapi.exception.AppException;
import pavlo.pro.massagetherapyapi.exception.ExceptionType;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.repository.RoleRepository;
import pavlo.pro.massagetherapyapi.service.RoleService;

import static pavlo.pro.massagetherapyapi.exception.EntityType.ROLE;

@Slf4j
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
            throw AppException.buildException(ROLE, ExceptionType.DUPLICATE_ENTITY, newRole.getName());
        }
        Role roleToCreate = new Role().setName(roleName);
        try {
            Role created = roleRepository.insert(roleToCreate);
            log.info("Created instance of Role with id: {}", created.getId());
            return created;
        } catch(Exception exception) {
            throw AppException.buildException(ROLE, ExceptionType.ENTITY_EXCEPTION);
        }
    }

    public List<Role> getAllRoles() throws RuntimeException {
        try {
            List<Role> foundRoles = roleRepository.findAll();
            log.info("Found all instances of Role, quantity: {}", foundRoles.size());
            return foundRoles;
        } catch(Exception exception) {
            throw AppException.buildException(ROLE, ExceptionType.ENTITY_EXCEPTION);
        }
    }

    public Role findRoleByName(String roleName) throws RuntimeException {
        try {
            Role found = roleRepository.findByName(roleName);
            log.info("Found instance of Role by name: {}; with id: {}", found.getName(), found.getId());
            return found;
        } catch(Exception exception) {
            throw AppException.buildException(ROLE, ExceptionType.ENTITY_NOT_FOUND, roleName);
        }
    }

}
