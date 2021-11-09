package com.senac.library.api.mother;

import com.senac.library.api.model.entities.CreditCard;

import java.util.ArrayList;
import java.util.List;

public class CreditCartMother {

    public static List<CreditCard> createCreditCards() {
        List<CreditCard> creditCards = new ArrayList<>();

        creditCards.add(createCreditCard());

        return creditCards;
    }

    public static CreditCard createCreditCard() {
        CreditCard creditCard = new CreditCard();

        creditCard.setId(1L);
        creditCard.setCpf("11122233344");
        creditCard.setName("name");
        creditCard.setNumber("1111222233334444");
        creditCard.setSecurityNumber("123");
        creditCard.setValidateData("10/29");

        return creditCard;
    }
}
