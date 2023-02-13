package pavlo.pro.massagetherapyapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pavlo.pro.massagetherapyapi.dto.BookingSlotDto;
import pavlo.pro.massagetherapyapi.dto.response.Response;
import pavlo.pro.massagetherapyapi.model.BookingSlot;
import pavlo.pro.massagetherapyapi.service.interfaces.BookingService;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    @PostMapping("/addBookingSlots")
    public Response addBookingSlots(
        @PathVariable("id") String massageId,
        @RequestBody @Valid List<BookingSlotDto> bookingSlotsDto
    ) {
        Boolean result = this.bookingService.addBookingSlots(bookingSlotsDto, massageId);
        return Response.ok().setPayload(result);
    }

}
