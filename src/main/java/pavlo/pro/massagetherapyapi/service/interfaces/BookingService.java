package pavlo.pro.massagetherapyapi.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pavlo.pro.massagetherapyapi.model.BookingSlot;

import java.util.List;

public interface BookingService {
    Page<BookingSlot> getBookingSlotsForMassageId(Pageable paging, String massageId);
    Boolean addBookingSlots(List<BookingSlot> bookingSlots);
    Boolean removeBookingSlot(BookingSlot bookingSlot);
}
