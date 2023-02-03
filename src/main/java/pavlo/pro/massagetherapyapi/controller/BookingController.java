package pavlo.pro.massagetherapyapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pavlo.pro.massagetherapyapi.dto.response.Response;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Response getAvailableSlotsPerMassageId(
        @PathVariable("id") String massageId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ) {
        Pageable paging = PageRequest.of(page, size);


        return Response.ok().setPayload(new ArrayList());
    }

}
