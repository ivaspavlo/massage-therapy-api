package pavlo.pro.massagetherapyapi.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pavlo.pro.massagetherapyapi.dto.BookingSlotDto;
import pavlo.pro.massagetherapyapi.exception.AppException;
import pavlo.pro.massagetherapyapi.exception.EntityType;
import pavlo.pro.massagetherapyapi.exception.ExceptionType;
import pavlo.pro.massagetherapyapi.model.BookingSlot;
import pavlo.pro.massagetherapyapi.repository.interfaces.BookingSlotRepository;
import pavlo.pro.massagetherapyapi.security.CustomUserDetails;
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

    public Boolean addBookingSlot(BookingSlotDto bookingSlotsDto, String massageId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) {
//            throw exception(EntityType.BOOKING_SLOT, ExceptionType.)
        }
        String userId = userDetails.getId();
        BookingSlot newBookingSlot = mapFromDto(bookingSlotsDto, massageId, userId);
        try {
            bookingSlotRepository.insert(newBookingSlot);
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
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return dateTime;
    }

    private BookingSlot mapFromDto(BookingSlotDto slotDto, String massageId, String userId) {
        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            startDate = convertDate(slotDto.getStart());
            endDate = convertDate(slotDto.getEnd());
            return new BookingSlot()
                    .setStart(startDate)
                    .setEnd(endDate)
                    .setMassageId(massageId)
                    .setUserId(userId);
        } catch (ParseException exception) {
            throw new RuntimeException();
        }
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return AppException.throwException(entityType, exceptionType, args);
    }

}
