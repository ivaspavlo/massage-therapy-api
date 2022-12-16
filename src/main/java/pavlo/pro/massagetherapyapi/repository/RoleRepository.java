package pavlo.pro.massagetherapyapi.repository;

import pavlo.pro.massagetherapyapi.model.Role;

public interface RoleRepository {
    Role findByRole(String role);
}
