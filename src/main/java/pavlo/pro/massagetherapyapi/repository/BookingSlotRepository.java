package pavlo.pro.massagetherapyapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import pavlo.pro.massagetherapyapi.model.BookingSlot;

public interface BookingSlotRepository extends MongoRepository<BookingSlot, String> {
    Page<BookingSlot> getBookingSlotsByMassageId(Pageable paging, String massageId);
}
