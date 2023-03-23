package pavlo.pro.massagetherapyapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pavlo.pro.massagetherapyapi.dto.BookingSlotDto;
import pavlo.pro.massagetherapyapi.dto.response.Response;
import pavlo.pro.massagetherapyapi.service.BookingService;

import javax.validation.Valid;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookingService bookingService;

    @PostMapping("/add/{massageId}")
    public Response<BookingSlotDto> addBookingSlots(
        TimeZone timeZone,
        @PathVariable("massageId") String massageId,
        @RequestBody @Valid BookingSlotDto bookingSlotsDto
    ) {
        BookingSlotDto bookingSlotDto = modelMapper.map(
            this.bookingService.addBookingSlot(timeZone, bookingSlotsDto, massageId),
            BookingSlotDto.class
        );
        return Response.ok().setPayload(bookingSlotDto);
    }

    @GetMapping("/{id}")
    public Response<List<BookingSlotDto>> getAvailableSlotsPerMassageId(
        @PathVariable("id") String massageId,
        @RequestParam(defaultValue = "2") int monthQty
    ) {
        List<BookingSlotDto> bookingSlotsDto = this.bookingService.getBookingSlotsPerMassageId(massageId, monthQty)
            .stream()
            .map(s -> modelMapper.map(s, BookingSlotDto.class))
            .collect(Collectors.toList());
        return Response.ok().setPayload(bookingSlotsDto);
    }

    @DeleteMapping("/{id}")
    public Response<BookingSlotDto> removeBookingSlot(
        @PathVariable("id") String bookingSlotId
    ) {
        return Response.ok().setPayload(
            this.bookingService.removeBookingSlot(bookingSlotId)
        );
    }

}
