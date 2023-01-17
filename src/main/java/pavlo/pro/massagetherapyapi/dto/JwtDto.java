package pavlo.pro.massagetherapyapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtDto {
    private String token;
    private String type = "Bearer";
    private String id;
    private List<String> roles;

    public JwtDto(String accessToken, String id, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.roles = roles;
    }
}
