package pavlo.pro.massagetherapyapi.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, String id, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.roles = roles;
    }
}
