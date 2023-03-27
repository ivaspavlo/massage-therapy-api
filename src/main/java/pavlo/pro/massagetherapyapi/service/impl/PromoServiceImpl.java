package pavlo.pro.massagetherapyapi.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pavlo.pro.massagetherapyapi.exception.AppException;
import pavlo.pro.massagetherapyapi.exception.ExceptionType;
import pavlo.pro.massagetherapyapi.model.Promo;
import pavlo.pro.massagetherapyapi.repository.PromoRepository;
import pavlo.pro.massagetherapyapi.service.PromoService;

import java.security.SecureRandom;
import java.util.Random;

import static pavlo.pro.massagetherapyapi.exception.EntityType.PROMO;

@Slf4j
@Component
@AllArgsConstructor
public class PromoServiceImpl implements PromoService {

    private PromoRepository promoRepository;

    public Promo createPromo() {
        Promo created = promoRepository.insert(new Promo(generatePromoCode()));
        log.info("Created instance of Promo with id: {}", created.getId());
        return created;
    }

    public Promo getPromo(String code) {
        Promo found = promoRepository.findByCode(code);
        if (found != null) {
            log.info("Found instance of Promo with id: {}", found.getId());
            return found;
        }
        throw AppException.buildException(PROMO, ExceptionType.ENTITY_NOT_FOUND, code);
    }

    public Promo deletePromo(String code) {
        Promo found = promoRepository.findByCode(code);
        if (found != null) {
            log.info("Delete instance of Promo with id: {}", found.getId());
            promoRepository.deleteById(found.getId());
            return found;
        }
        throw AppException.buildException(PROMO, ExceptionType.ENTITY_NOT_FOUND, code);
    }

    private String generatePromoCode() {
        char[] chars = "ABCDEFGHIJKLMNOPRQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 1; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            if (i % 5 == 0) {
                sb.append("-");
            }
            sb.append(c);
        }
        return sb.toString();
    }

}
