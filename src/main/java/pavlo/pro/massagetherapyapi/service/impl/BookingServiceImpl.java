package pavlo.pro.massagetherapyapi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pavlo.pro.massagetherapyapi.model.BookingSlot;
import pavlo.pro.massagetherapyapi.repository.BookingSlotRepository;
import pavlo.pro.massagetherapyapi.service.interfaces.BookingService;

@Component
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingSlotRepository bookingSlotRepository;

    public Page<BookingSlot> getBookingSlotsForMassageId() {
//        https://www.baeldung.com/queries-in-spring-data-mongodb

    }

}
