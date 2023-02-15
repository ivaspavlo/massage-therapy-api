package pavlo.pro.massagetherapyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pavlo.pro.massagetherapyapi.dto.RoleDto;
import pavlo.pro.massagetherapyapi.dto.request.UpdateUserReq;
import pavlo.pro.massagetherapyapi.dto.response.Response;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.service.interfaces.RoleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public Response createRole(
        @RequestBody @Valid RoleDto roleDto
    ) {
        Role createdRole = roleService.createRole(roleDto);
        return Response.ok().setPayload(createdRole);
    }

    @GetMapping("")
    public Response getAllRoles() {
        return Response.ok().setPayload(true);
    }

    @GetMapping("/{name}")
    public Response findRoleByName(
        @PathVariable("name") String roleName
    ) {
        return Response.ok().setPayload(true);
    }

}
