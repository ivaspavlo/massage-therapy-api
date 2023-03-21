package pavlo.pro.massagetherapyapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pavlo.pro.massagetherapyapi.dto.RoleDto;
import pavlo.pro.massagetherapyapi.dto.response.Response;
import pavlo.pro.massagetherapyapi.model.Role;
import pavlo.pro.massagetherapyapi.service.RoleService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public Response<Role> createRole(
        @RequestBody @Valid RoleDto roleDto
    ) {
        Role createdRole = roleService.createRole(roleDto);
        return Response.ok().setPayload(
            modelMapper.map(createdRole, RoleDto.class)
        );
    }

    @GetMapping("")
    public Response<List<RoleDto>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        List<RoleDto> rolesDto = roles.stream()
            .map((role) -> modelMapper.map(role, RoleDto.class))
            .collect(Collectors.toList());
        return Response.ok().setPayload(rolesDto);
    }

    @GetMapping("/{name}")
    public Response<RoleDto> findRoleByName(
        @PathVariable("name") String roleName
    ) {
        return Response.ok().setPayload(
            modelMapper.map(roleService.findRoleByName(roleName), RoleDto.class)
        );
    }

}
