package com.example.sbjpacreditcard.repository;

import com.example.sbjpacreditcard.domain.CreditCard;
import com.example.sbjpacreditcard.services.EncryptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@DataJpaTest
@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCarsRepositoryTest {

    private final String CREDIT_CARD_NUMBER = "1234567898765";

    @Autowired
    private CreditCardRepository creditCardRepository;

//    @Autowired
//    JdbcTemplate jdbcTemplate;

    @Autowired
    private EncryptionService encryptionService;
    @Test
    void workCreditCarsRepositoryTest() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD_NUMBER);
        creditCard.setCvv("800");
        creditCard.setExpirationDate("12/2030");
        CreditCard save = creditCardRepository.saveAndFlush(creditCard);

//        Map<String, Object> dbRow = jdbcTemplate.queryForMap("SELECT * FROM credit_card where id = " + save.getId());
//        String creditCardNumber = (String) dbRow.get("credit_card_number");
//
//        assertThat(save.getCreditCardNumber()).isNotEqualTo(creditCardNumber);
//        assertThat(creditCardNumber).isEqualTo(encryptionService.encrypt(save.getCreditCardNumber()));


        CreditCard creditCard1 = creditCardRepository.findById(save.getId()).get();
        assertThat(creditCard1.getCreditCardNumber()).isEqualTo(CREDIT_CARD_NUMBER);

    }
}