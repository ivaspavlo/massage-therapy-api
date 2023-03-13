package pavlo.pro.massagetherapyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pavlo.pro.massagetherapyapi.dto.response.Response;
import pavlo.pro.massagetherapyapi.service.PromoService;

@RestController
@RequestMapping("/api/v1/promo")
public class PromoController {

    @Autowired
    private PromoService promoService;

    @PostMapping
    public Response createPromo() {
        return Response.ok().setPayload(
            promoService.createPromo()
        );
    }

    @GetMapping("/{promo}")
    public Response getPromo(
        @PathVariable("id") String promo
    ) {
        return Response.ok().setPayload(
            promoService.getPromo(promo)
        );
    }

}
