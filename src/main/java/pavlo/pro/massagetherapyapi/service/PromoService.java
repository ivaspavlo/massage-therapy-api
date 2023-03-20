package pavlo.pro.massagetherapyapi.service;

import pavlo.pro.massagetherapyapi.model.Promo;

public interface PromoService {
    public Promo createPromo();
    public Promo getPromo(String promoCode);
    public Promo deletePromo(String promoCode);
}
