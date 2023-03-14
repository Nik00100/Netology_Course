package ru.netology.springboot_courseproject_moneytransferservice.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.netology.springboot_courseproject_moneytransferservice.dao.DatabaseCards;
import ru.netology.springboot_courseproject_moneytransferservice.logger.Logger;
import ru.netology.springboot_courseproject_moneytransferservice.model.Card;
import ru.netology.springboot_courseproject_moneytransferservice.model.MoneyTransfer;

import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CardRepository {

    private final Logger logger = Logger.getLog();
    private static AtomicLong operationId;
    private final DatabaseCards databaseCards;

    @Autowired
    public CardRepository() {
        databaseCards = new DatabaseCards();
        logger.log("База данных карт пользователей загружена");
        operationId = new AtomicLong();
    }

    public String verificationCard(MoneyTransfer moneyTransfer) {
        Card cardSender;
        if (databaseCards.getListCards().containsKey(moneyTransfer.getCardFromNumber())) {
            cardSender = databaseCards.getListCards().get(moneyTransfer.getCardFromNumber());
            if (!databaseCards.getListCards().containsKey(moneyTransfer.getCardToNumber())) {
                logger.log(String.format("Номер карты получателя № %s введен НЕ верно!", moneyTransfer.getCardToNumber()));
                return null;
            } else if (!moneyTransfer.getCardFromValidTill().equals(cardSender.getCardValid())) {
                logger.log(String.format("Дата действия карты отправителя: %s введена НЕ верно!", moneyTransfer.getCardFromValidTill()));
                return null;
            } else if (!moneyTransfer.getCardFromCVV().equals(cardSender.getCardCVV())) {
                logger.log(String.format("Код CVV карты отправителя: %s введен НЕ верно!", moneyTransfer.getCardFromCVV()));
                return null;
            } else if (!moneyTransfer.getAmount().getCurrency().equals(cardSender.getCurrency())) {
                logger.log(String.format("Значение валюты перевода: %s введено НЕ верно!", moneyTransfer.getAmount().getCurrency()));
                return null;
            } else if (moneyTransfer.getAmount().getValue() > cardSender.getAccount()) {
                logger.log("Сумма перевода превышает сумму счета отправителя!");
                return null;
            } else {
                operationId.getAndIncrement();
                logger.log(String.format("Карта отправителя № %s проверена", moneyTransfer.getCardFromNumber()));
                logger.log(String.format("Карта получателя № %s проверена", moneyTransfer.getCardToNumber()));
                return String.format("operationId: %d has been verified", operationId.get());
            }
        } else {
            logger.log(String.format("Номер карты отправителя №  %s введен НЕ верно!", moneyTransfer.getCardFromNumber()));
            return null;
        }
    }

    /*public String confirmOperation(String code) {
        if (code.equals(confirmationCode)) {
            logger.log("Код потверждения операции введен верно");
            return String.format("operationId: %d is completed", operationId.get());
        }
        logger.log("Код потверждения операции введен НЕ верно!!!");
        return null;
    }*/
}
