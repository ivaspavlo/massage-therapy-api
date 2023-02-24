package pavlo.pro.massagetherapyapi.repository;

import pavlo.pro.massagetherapyapi.model.BookingSlot;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingSlotRepositoryCustom {
    public List<BookingSlot> getBookingSlotsPerMassageId(LocalDateTime dateFrom, LocalDateTime dateTo, String massageId);
}
