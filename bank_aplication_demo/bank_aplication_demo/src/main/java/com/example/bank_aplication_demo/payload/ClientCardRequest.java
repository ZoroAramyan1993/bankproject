package com.example.bank_aplication_demo.payload;


public class ClientCardRequest {
//private Long cardId;
private Long cardNumber;
private Long secretNumber;
private Long account;
private Long money;

//    public Long getCardId() {
//        return cardId;
//    }
//
//    public void setCardId(Long cardId) {
//        this.cardId = cardId;
//    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getSecretNumber() {
        return secretNumber;
    }

    public void setSecretNumber(Long secretNumber) {
        this.secretNumber = secretNumber;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
}
