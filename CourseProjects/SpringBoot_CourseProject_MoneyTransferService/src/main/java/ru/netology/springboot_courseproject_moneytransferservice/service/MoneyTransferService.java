package ru.netology.springboot_courseproject_moneytransferservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.springboot_courseproject_moneytransferservice.dao.DatabaseCards;
import ru.netology.springboot_courseproject_moneytransferservice.exception.DataMismatch;
import ru.netology.springboot_courseproject_moneytransferservice.exception.IncorrectDataEntry;
import ru.netology.springboot_courseproject_moneytransferservice.logger.Logger;
import ru.netology.springboot_courseproject_moneytransferservice.model.MoneyTransfer;
import ru.netology.springboot_courseproject_moneytransferservice.repository.CardRepository;

@Service
public class MoneyTransferService {

    private final Logger logger = Logger.getLog();
    private final CardRepository cardRepository;
    private MoneyTransfer moneyTransfer;
    private final String confirmationCode;
    private final DatabaseCards databaseCards;

    @Autowired
    public MoneyTransferService(CardRepository customerRepository) {
        databaseCards = new DatabaseCards();
        confirmationCode = databaseCards.getConfirmationCode();
        this.cardRepository = customerRepository;
    }

    public String verificationCard(MoneyTransfer moneyTransfer) {
        this.moneyTransfer = moneyTransfer;
        if (isEmpty(moneyTransfer.getCardFromNumber()) || isEmpty(moneyTransfer.getCardFromValidTill()) ||
                isEmpty(moneyTransfer.getCardFromCVV()) || isEmpty(moneyTransfer.getCardToNumber())) {
            logger.log("ERROR! Ошибка ввода данных карты: Error input data card");
            throw new IncorrectDataEntry("Error input data card");
        }
        if (isEmpty(moneyTransfer.getAmount().getCurrency()) || isEmptyNumber(moneyTransfer.getAmount().getValue())) {
            logger.log("ERROR! Ошибка ввода суммы: Error input data amount");
            throw new IncorrectDataEntry("Error input data amount");
        }
        String operationId = cardRepository.verificationCard(moneyTransfer);
        if (isEmpty(operationId)) {
            logger.log("ERROR! Ошибка перевода: Error transfer");
            throw new DataMismatch("Error transfer");
        }
        return operationId;
    }

    public String confirmOperation(String code) {
        if (isEmpty(code)) {
            logger.log("ERROR! Ошибка ввода кода подтверждения: Error input data code");
            throw new IncorrectDataEntry("Error input data code");
        }
        String operationId = confirmation(code);
        if (isEmpty(operationId)) {
            logger.log("ERROR! Ошибка перевода: Error transfer");
            throw new DataMismatch("Error transfer");
        }

        logger.log("Операция: \"" + operationId + "\" выполнена \n" +
                "Карта списания: " + moneyTransfer.getCardFromNumber() + "\n" +
                "Карта зачисления: " + moneyTransfer.getCardToNumber() + "\n" +
                "Сумма перевода: " + moneyTransfer.getAmount().getValue() + "\n" +
                "Валюта перевода: " + moneyTransfer.getAmount().getCurrency());
        return operationId;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmptyNumber(int num) {
        return num <= 0;
    }

    private String confirmation(String code) {
        if (code.equals(confirmationCode)) {
            logger.log("Код потверждения операции введен верно");
            return "operation is completed";
        }
        logger.log("Код потверждения операции введен НЕ верно!!!");
        return null;
    }
}
