package com.codecrackers.bankapi.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionType {
    P2P ("P2P"),
    Deposit ("Deposit"),
    Withdrawal("Withdrawal");


    private final String type;


    private TransactionType (String type){
        this.type = type;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.type;
    }


    public static TransactionType fromString(String typeString) {
        for (TransactionType type : TransactionType.values()) {
            if (type.toString().equalsIgnoreCase(typeString)) {
                return type;
            }
        }
        return null;
    }



}
