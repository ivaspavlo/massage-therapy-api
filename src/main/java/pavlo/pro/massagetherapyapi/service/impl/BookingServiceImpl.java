package pavlo.pro.massagetherapyapi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pavlo.pro.massagetherapyapi.model.BookingSlot;
import pavlo.pro.massagetherapyapi.repository.BookingSlotRepository;
import pavlo.pro.massagetherapyapi.service.interfaces.BookingService;

import java.util.List;

@Component
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingSlotRepository bookingSlotRepository;

    public Page<BookingSlot> getBookingSlotsForMassageId(Pageable paging, String massageId) {
        return bookingSlotRepository.getBookingSlotsByMassageId(paging, massageId);
    }

    public Boolean addBookingSlots(List<BookingSlot> bookingSlots) {
        try {
            bookingSlotRepository.insert(bookingSlots);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public Boolean removeBookingSlot(BookingSlot bookingSlot) {
        try {
            bookingSlotRepository.delete(bookingSlot);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

}
