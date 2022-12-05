package pavlo.pro.massage.therapy.api.dto.mapper;

import pavlo.pro.massage.therapy.api.dto.model.RoleDto;
import pavlo.pro.massage.therapy.api.dto.model.UserDto;
import pavlo.pro.massage.therapy.api.model.User;

import java.util.HashSet;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return new UserDto()
                .setId(user.getId())
                .setPassword(user.getPassword())
                .setFirstName(user.getFirstName())
                .setBirth(user.getBirth())
                .setEmail(user.getEmail())
                .setBookings(user.getBookings())
                .setArticles(user.getArticles())
                .setPhone(user.getPhone())
                .setBookings(user.getBookings())
                .setArticles(user.getArticles())
                .setRoles(user.getRoles(new HashSet<RoleDto>(user
                    .getRoles()
                    .stream()
                    .map(role -> new ModelMapper().map(role, RoleDto.class))
                    .collect(Collectors.toSet())))
                );
    }
}
