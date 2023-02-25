package pavlo.pro.massagetherapyapi.service.impl;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pavlo.pro.massagetherapyapi.controller.BookingController;
import pavlo.pro.massagetherapyapi.dto.BookingSlotDto;
import pavlo.pro.massagetherapyapi.exception.AppException;
import pavlo.pro.massagetherapyapi.exception.EntityType;
import pavlo.pro.massagetherapyapi.exception.ExceptionType;
import pavlo.pro.massagetherapyapi.model.BookingSlot;
import pavlo.pro.massagetherapyapi.model.Product;
import pavlo.pro.massagetherapyapi.repository.BookingSlotRepository;
import pavlo.pro.massagetherapyapi.security.CustomUserDetails;
import pavlo.pro.massagetherapyapi.service.interfaces.BookingService;
import pavlo.pro.massagetherapyapi.service.interfaces.ProductService;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

@Component
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private static final Logger logger = LogManager.getLogger(BookingController.class);

    @Autowired
    BookingSlotRepository bookingSlotRepository;

    @Autowired
    ProductService productService;

    public List<BookingSlot> getBookingSlotsPerMassageId(
        String massageId,
        Integer monthQty
    ) {
        LocalDateTime dateFrom = LocalDateTime.now();
        LocalDateTime dateTo = dateFrom.plusMonths(monthQty);
        return bookingSlotRepository.getBookingSlotsPerMassageId(dateFrom, dateTo, massageId);
    }

    public BookingSlot addBookingSlot(
        TimeZone timeZone,
        BookingSlotDto bookingSlotsDto,
        String massageId
    ) throws RuntimeException {
        Product massage = productService.getProductById(massageId);
        if (massage == null) {
            throw exception(EntityType.PRODUCT, ExceptionType.ENTITY_NOT_FOUND, massageId);
        }
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getId();
        BookingSlot newBookingSlot = mapBookingSlotFromDto(bookingSlotsDto, massageId, userId, timeZone.getID());
        try {
            BookingSlot created = bookingSlotRepository.insert(newBookingSlot);
            logger.info("Created instance of BookingSlot with id: {}", created.getId());
            return created;
        } catch (Exception exception) {
            throw exception(EntityType.PRODUCT, ExceptionType.DATABASE_EXCEPTION);
        }
    }

    public Boolean removeBookingSlot(
        String bookingSlotId
    ) throws RuntimeException {
        try {
            bookingSlotRepository.deleteById(bookingSlotId);
            return true;
        } catch (Exception error) {
            throw exception(EntityType.PRODUCT, ExceptionType.DATABASE_EXCEPTION);
        }
    }

    private LocalDateTime convertDate(String date) throws ParseException {
        return LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(date));
    }

    private BookingSlot mapBookingSlotFromDto(
        BookingSlotDto slotDto,
        String massageId,
        String userId,
        String timeZone
    ) {
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

    private RuntimeException exception(
        EntityType entityType,
        ExceptionType exceptionType,
        String... args
    ) {
        return AppException.throwException(entityType, exceptionType, args);
    }

}
