package pavlo.pro.massagetherapyapi.repository.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;
import pavlo.pro.massagetherapyapi.model.BookingSlot;

public interface BookingSlotRepository extends MongoRepository<BookingSlot, String>, BookingSlotCustomRepository { }
