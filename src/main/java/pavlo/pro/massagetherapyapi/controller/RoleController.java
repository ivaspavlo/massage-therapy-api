package pavlo.pro.massagetherapyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pavlo.pro.massagetherapyapi.dto.response.Response;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.service.interfaces.RoleService;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("")
    public Response createRole() {
        Role createdRole = roleService.createRole();
        return Response.ok().setPayload(true);
    }

    @GetMapping("")
    public Response getAllRoles() {
        return Response.ok().setPayload(true);
    }

    @GetMapping("")
    public Response findRoleByName() {
        return Response.ok().setPayload(true);
    }

}
