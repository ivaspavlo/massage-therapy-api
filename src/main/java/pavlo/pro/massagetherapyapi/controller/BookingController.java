package pavlo.pro.massagetherapyapi.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pavlo.pro.massagetherapyapi.dto.BookingSlotDto;
import pavlo.pro.massagetherapyapi.dto.response.Response;
import pavlo.pro.massagetherapyapi.service.interfaces.BookingService;

import javax.validation.Valid;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/add/{massageId}")
    public Response addBookingSlots(
        TimeZone timeZone,
        @PathVariable("massageId") String massageId,
        @RequestBody @Valid BookingSlotDto bookingSlotsDto
    ) {
        return Response.ok().setPayload(
            this.bookingService.addBookingSlot(timeZone, bookingSlotsDto, massageId)
        );
    }

    @GetMapping("/{id}")
    public Response getAvailableSlotsPerMassageId(
        @PathVariable("id") String massageId,
        @RequestParam(defaultValue = "2") int monthQty
    ) {
        return Response.ok().setPayload(
            this.bookingService.getBookingSlotsPerMassageId(massageId, monthQty)
        );
    }

    @DeleteMapping("/{id}")
    public Response removeBookingSlot(
        @PathVariable("id") String bookingSlotId
    ) {
        return Response.ok().setPayload(
            this.bookingService.removeBookingSlot(bookingSlotId)
        );
    }

}
