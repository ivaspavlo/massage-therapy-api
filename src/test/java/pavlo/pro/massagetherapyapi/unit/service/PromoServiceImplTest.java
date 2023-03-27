package pavlo.pro.massagetherapyapi.unit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pavlo.pro.massagetherapyapi.model.Promo;
import pavlo.pro.massagetherapyapi.repository.PromoRepository;
import pavlo.pro.massagetherapyapi.service.impl.PromoServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PromoServiceImplTest {

    static final String testPromoCode = "0000-0000-0000-0000";
    @Mock
    private PromoRepository mockPromoRepository;
    private PromoServiceImpl mockPromoServiceImpl;
    private Promo mockPromo;

    @BeforeEach
    private void setupTestData() {
        mockPromoServiceImpl = new PromoServiceImpl(mockPromoRepository);
        mockPromo = new Promo().setCode(testPromoCode);
    }

    @DisplayName("When createPromo is triggered should return Promo instance.")
    @Test
    public void whenCreatePromo_shouldReturnPromoInstance() {
        when(mockPromoRepository.insert(ArgumentMatchers.any(Promo.class))).thenReturn(mockPromo);
        Promo created = mockPromoServiceImpl.createPromo();
        System.out.println(created);
        assertThat(created).isInstanceOf(Promo.class);
    }

    @DisplayName("When getPromo is triggered should return Promo instance.")
    @Test
    public void whenGetPromo_shouldReturnPromoInstance() {
        when(mockPromoRepository.findByCode(testPromoCode)).thenReturn(mockPromo);
        Promo found = mockPromoServiceImpl.getPromo(testPromoCode);
        System.out.println(found);
        assertThat(found).isInstanceOf(Promo.class);
    }

    @DisplayName("When getPromo is triggered should throw RuntimeException.")
    @Test
    public void whenGetPromo_shouldReturnRuntimeException() {
        RuntimeException thrown = assertThrows(
            RuntimeException.class,
            () -> {
                when(mockPromoRepository.findByCode(testPromoCode)).thenReturn(null);
                mockPromoServiceImpl.getPromo(testPromoCode);
            },
            "Should throw when Promo with the provided code doesn't exist."
        );
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

}
