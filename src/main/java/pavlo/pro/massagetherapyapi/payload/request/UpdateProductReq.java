package pavlo.pro.massagetherapyapi.payload.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateProductReq {
    public String title;
    public String subtitle;
    public String price;
    public String desc;
}
