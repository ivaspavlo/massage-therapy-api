package pavlo.pro.massagetherapyapi.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pavlo.pro.massagetherapyapi.dto.BookingSlotDto;
import pavlo.pro.massagetherapyapi.model.BookingSlot;
import java.util.TimeZone;

public interface BookingService {
    Page<BookingSlot> getAvailableSlotsPerMassageId(Pageable paging, String massageId);
    BookingSlot addBookingSlot(TimeZone timeZone, BookingSlotDto bookingSlotsDto, String massageId);
    Boolean removeBookingSlot(BookingSlot bookingSlot);
}
