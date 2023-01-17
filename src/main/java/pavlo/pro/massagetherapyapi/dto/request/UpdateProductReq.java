package pavlo.pro.massagetherapyapi.dto.request;

import lombok.Data;

@Data
public class UpdateProductReq {
    public String title;
    public String subtitle;
    public String price;
    public String desc;
}
