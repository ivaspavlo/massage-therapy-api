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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingSlotRepository bookingSlotRepository;

    public Page<BookingSlot> getAvailableSlotsPerMassageId(Pageable paging, String massageId) {
        return bookingSlotRepository.getBookingSlotsByMassageId(paging, massageId);
    }

    public BookingSlot addBookingSlot(
        TimeZone timeZone,
        BookingSlotDto bookingSlotsDto,
        String massageId
    ) throws RuntimeException {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getId();
        BookingSlot newBookingSlot = mapFromDto(bookingSlotsDto, massageId, userId, timeZone.getID());
        return bookingSlotRepository.insert(newBookingSlot);
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
        return LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(date));
    }

    private BookingSlot mapFromDto(BookingSlotDto slotDto, String massageId, String userId, String timeZone) {
        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            startDate = convertDate(slotDto.getStart());
            endDate = convertDate(slotDto.getEnd());
            return new BookingSlot()
                .setStart(startDate)
                .setEnd(endDate)
                .setMassageId(massageId)
                .setUserId(userId)
                .setTimeZone(timeZone);
        } catch (ParseException exception) {
            throw exception(EntityType.BOOKING_SLOT, ExceptionType.PARSE_EXCEPTION);
        }
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return AppException.throwException(entityType, exceptionType, args);
    }

}
