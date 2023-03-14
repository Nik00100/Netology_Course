package ru.netology.springboot_courseproject_moneytransferservice.model;

import java.util.Objects;

public class MoneyTransfer {
    /**
     * @param cardFromNumber - номер карты с которой осуществляется перевод.
     */
    private String cardFromNumber;
    /**
     * @param cardFromValidTill - число до которого действует карта,с которой осуществляется перевод.
     */
    private String cardFromValidTill;
    /**
     * @param cardFromCVV - CVV - номер карты,с которой осуществляется перевод.
     */
    private String cardFromCVV;
    /**
     * @param cardToNumber - номер карты, на которую осуществляется перевод.
     */
    private String cardToNumber;
    /**
     * @param amount - сумма и валюта перевода.
     */
    private Amount amount;

    public MoneyTransfer(String cardFromNumber, String cardFromValidTill, String cardFromCVV, String cardToNumber, int value, String currency) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
        amount = new Amount(value, currency);
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public void setCardFromNumber(String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public void setCardFromValidTill(String cardFromValidTill) {
        this.cardFromValidTill = cardFromValidTill;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public void setCardFromCVV(String cardFromCVV) {
        this.cardFromCVV = cardFromCVV;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public void setCardToNumber(String cardToNumber) {
        this.cardToNumber = cardToNumber;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardFromValidTill='" + cardFromValidTill + '\'' +
                ", cardFromCVV='" + cardFromCVV + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MoneyTransfer verificationCard = (MoneyTransfer) obj;
        return Objects.equals(cardFromNumber, verificationCard.cardFromNumber) &&
                Objects.equals(cardFromValidTill, verificationCard.cardFromValidTill) &&
                Objects.equals(cardFromCVV, verificationCard.cardFromCVV) &&
                Objects.equals(cardToNumber, verificationCard.cardToNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardFromNumber, cardFromValidTill, cardFromCVV, cardToNumber, amount);
    }
}
