package pavlo.pro.massagetherapyapi.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pavlo.pro.massagetherapyapi.model.BookingSlot;

public interface BookingService {
    Page<BookingSlot> getBookingSlotsForMassageId(Pageable paging, String massageId);
}
