package ru.netology.springboot_courseproject_moneytransferservice.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.springboot_courseproject_moneytransferservice.exception.DataMismatch;
import ru.netology.springboot_courseproject_moneytransferservice.exception.IncorrectDataEntry;
import ru.netology.springboot_courseproject_moneytransferservice.model.MoneyTransfer;
import ru.netology.springboot_courseproject_moneytransferservice.repository.CardRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTransferServiceTest {


    private final CardRepository cardRepository = new CardRepository();
    private final MoneyTransferService moneyTransferService = new MoneyTransferService(cardRepository);
    private MoneyTransfer verificationCard;

    @BeforeEach
    public void init() {
        verificationCard = new MoneyTransfer("cardFromNumber", "cardFromValidTill",
                "cardFromCVV", "cardToNumber", 10000, "currency");
    }

    @Test
    public void test_IncorrectDataEntry_errorInputDataCard() {
        verificationCard.setCardFromNumber(null);
        Exception exception = assertThrows(IncorrectDataEntry.class, () -> moneyTransferService.verificationCard(verificationCard));
        assertEquals("Error input data card", exception.getMessage());
    }

    @Test
    public void test_IncorrectDataEntry_errorInputDataAmount() {
        verificationCard.getAmount().setValue(0);
        Exception exception = assertThrows(IncorrectDataEntry.class, () -> moneyTransferService.verificationCard(verificationCard));
        assertEquals("Error input data amount", exception.getMessage());
    }

    @Test
    public void test_IncorrectDataEntry_errorInputDataCode() {
        String code = null;
        Exception exception = assertThrows(IncorrectDataEntry.class, () -> moneyTransferService.confirmOperation(code));
        assertEquals("Error input data code", exception.getMessage());
    }

    @Test
    public void test_DataMismatch_verificationCard() {
        Exception exception1 = assertThrows(DataMismatch.class, () -> moneyTransferService.verificationCard(verificationCard));
        assertEquals("Error transfer", exception1.getMessage());
    }

    @Test
    public void test_DataMismatch_confirmOperation() {
        String code = "code";
        Exception exception2 = assertThrows(DataMismatch.class, () -> moneyTransferService.confirmOperation(code));
        assertEquals("Error transfer", exception2.getMessage());
    }

}
