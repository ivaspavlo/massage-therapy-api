package pavlo.pro.massagetherapyapi.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateProductReq {
    @NotBlank
    private String title;
    @NotBlank
    private String price;
    @NotBlank
    private String subtitle;
    @NotBlank
    private String desc;
}
