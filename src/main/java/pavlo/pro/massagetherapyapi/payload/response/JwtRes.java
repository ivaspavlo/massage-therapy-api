package pavlo.pro.massagetherapyapi.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtRes {
    private String token;
    private String type = "Bearer";
    private String id;
    private List<String> roles;

    public JwtRes(String accessToken, String id, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.roles = roles;
    }
}
