package pavlo.pro.massagetherapyapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pavlo.pro.massagetherapyapi.dto.BookingSlotDto;
import pavlo.pro.massagetherapyapi.dto.response.Response;
import pavlo.pro.massagetherapyapi.model.BookingSlot;
import pavlo.pro.massagetherapyapi.service.interfaces.BookingService;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Response getAvailableSlotsPerMassageId(
        @PathVariable("id") String massageId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        return Response.ok().setPayload(
            this.bookingService.getAvailableSlotsPerMassageId(paging, massageId)
        );
    }

    @PostMapping("/add/{id}")
    public Response addBookingSlots(
        TimeZone timeZone,
        @PathVariable("id") String productId,
        @RequestBody @Valid BookingSlotDto bookingSlotsDto
    ) {
        BookingSlot result = this.bookingService.addBookingSlot(timeZone, bookingSlotsDto, productId);
        // TODO: add proper logging for booking slot creation
        System.out.println("Created instance of BookingSlot with id: " + result.getId());
        return Response.ok().setPayload(result);
    }

}
