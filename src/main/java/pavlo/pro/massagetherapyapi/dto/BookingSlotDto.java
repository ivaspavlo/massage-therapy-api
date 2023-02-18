package pavlo.pro.massagetherapyapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookingSlotDto {
    @NotBlank
    private String start;
    @NotBlank
    private String end;
    private String id;
    private String massageId;
}
