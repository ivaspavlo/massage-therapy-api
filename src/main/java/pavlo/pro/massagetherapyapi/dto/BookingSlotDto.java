package pavlo.pro.massagetherapyapi.dto;

import lombok.Data;

@Data
public class BookingSlotDto {
    private String id;
    private String start;
    private String end;
    private String massageId;
}
