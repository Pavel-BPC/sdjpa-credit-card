package com.example.sbjpacreditcard.repository;

import com.example.sbjpacreditcard.domain.CreditCard;
import com.example.sbjpacreditcard.services.EncryptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@DataJpaTest
@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCarsRepositoryTest {

    private final String CREDIT_CARD_NUMBER = "1234567898765";

    @Autowired
    private CreditCarsRepository creditCarsRepository;

    @Autowired
    private EncryptionService encryptionService;
    @Test
    void workCreditCarsRepositoryTest() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD_NUMBER);
        creditCard.setCvv("800");
        creditCard.setExpirationDate("12/2030");

        CreditCard save = creditCarsRepository.saveAndFlush(creditCard);
        CreditCard creditCard1 = creditCarsRepository.findById(save.getId()).get();
        assertThat(creditCard1.getCreditCardNumber()).isEqualTo(CREDIT_CARD_NUMBER);

    }
}