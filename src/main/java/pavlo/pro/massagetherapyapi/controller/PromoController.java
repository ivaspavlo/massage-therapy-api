package pavlo.pro.massagetherapyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pavlo.pro.massagetherapyapi.dto.response.Response;
import pavlo.pro.massagetherapyapi.model.Promo;
import pavlo.pro.massagetherapyapi.service.PromoService;

@RestController
@RequestMapping("/api/v1/promo")
public class PromoController {

    @Autowired
    private PromoService promoService;

    @PostMapping
    public Response<Promo> createPromo() {
        return Response.ok().setPayload(
            promoService.createPromo()
        );
    }

    @GetMapping("{promoCode}")
    public Response<Promo> getPromo(
        @PathVariable("promoCode") String promoCode
    ) {
        return Response.ok().setPayload(
            promoService.getPromo(promoCode)
        );
    }

    @DeleteMapping("{promoCode}")
    public Response<Promo> deletePromo(
        @PathVariable("promoCode") String promoCode
    ) {
        return Response.ok().setPayload(
            promoService.deletePromo(promoCode)
        );
    }

}
