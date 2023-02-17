package pavlo.pro.massagetherapyapi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pavlo.pro.massagetherapyapi.dto.BookingSlotDto;
import pavlo.pro.massagetherapyapi.model.BookingSlot;
import pavlo.pro.massagetherapyapi.repository.interfaces.BookingSlotRepository;
import pavlo.pro.massagetherapyapi.service.interfaces.BookingService;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingSlotRepository bookingSlotRepository;

    public Page<BookingSlot> getAvailableSlotsPerMassageId(Pageable paging, String massageId) {
        return bookingSlotRepository.getBookingSlotsByMassageId(paging, massageId);
    }

    public Boolean addBookingSlots(List<BookingSlotDto> bookingSlotsDto, String massageId) {
        List<BookingSlot> newBookingSlots = bookingSlotsDto.stream()
            .map((BookingSlotDto slotDto) -> mapFromDto(slotDto, massageId))
            .collect(Collectors.toList());
        try {
            bookingSlotRepository.insert(newBookingSlots);
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

    private LocalDateTime convertDate(String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(date,formatter);
        return dateTime;
    }

    private BookingSlot mapFromDto(BookingSlotDto slotDto, String massageId) {
        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            startDate = convertDate(slotDto.getStart());
            endDate = convertDate(slotDto.getEnd());
            return new BookingSlot()
                    .setStart(startDate)
                    .setEnd(endDate)
                    .setMassageId(massageId);
        } catch (ParseException exception) {
            throw new RuntimeException();
        }
    }

}
