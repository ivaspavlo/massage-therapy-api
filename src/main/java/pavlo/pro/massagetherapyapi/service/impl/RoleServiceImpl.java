package pavlo.pro.massagetherapyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pavlo.pro.massagetherapyapi.exception.AppException;
import pavlo.pro.massagetherapyapi.exception.EntityType;
import pavlo.pro.massagetherapyapi.exception.ExceptionType;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.repository.RoleRepository;
import pavlo.pro.massagetherapyapi.service.interfaces.RoleService;

import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role newRole) throws RuntimeException {
        try {
            return roleRepository.insert(newRole);
        } catch(Exception exception) {
            throw exception(EntityType.ROLE, ExceptionType.ENTITY_EXCEPTION);
        }
    }

    public List<Role> getAllRoles() {
        try {
            return roleRepository.findAll();
        } catch(Exception exception) {
            throw exception(EntityType.ROLE, ExceptionType.ENTITY_EXCEPTION);
        }
    }

    public Role findRoleByName(String name) {
        try {
            return roleRepository.findByName(name);
        } catch(Exception exception) {
            throw exception(EntityType.ROLE, ExceptionType.ENTITY_EXCEPTION);
        }
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return AppException.throwException(entityType, exceptionType, args);
    }

}
