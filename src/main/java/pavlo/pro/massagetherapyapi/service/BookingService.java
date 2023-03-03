package pavlo.pro.massagetherapyapi.service;

import pavlo.pro.massagetherapyapi.dto.BookingSlotDto;
import pavlo.pro.massagetherapyapi.model.BookingSlot;

import java.util.List;
import java.util.TimeZone;

public interface BookingService {
    List<BookingSlot> getBookingSlotsPerMassageId(String massageId, Integer monthQty);
    BookingSlot addBookingSlot(TimeZone timeZone, BookingSlotDto bookingSlotsDto, String massageId);
    Boolean removeBookingSlot(String bookingSlotId);
}
