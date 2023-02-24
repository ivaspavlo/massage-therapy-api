package pavlo.pro.massagetherapyapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pavlo.pro.massagetherapyapi.model.BookingSlot;

public interface BookingSlotRepository extends MongoRepository<BookingSlot, String>, BookingSlotRepositoryCustom { }
