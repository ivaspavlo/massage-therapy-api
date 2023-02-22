package pavlo.pro.massagetherapyapi.repository.interfaces;

import pavlo.pro.massagetherapyapi.model.BookingSlot;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingSlotCustomRepository {
    List<BookingSlot> getBookingSlotsPerMassageId(LocalDateTime dateFrom, LocalDateTime dateTo, String massageId);
}
