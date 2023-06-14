package com.codecrackers.bankapi.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionMedium {

    BALANCE("Balance"),
    REWARDS("Rewards");


    private final String medium;


    private TransactionMedium(String medium) {
        this.medium = medium;
    }


    @Override
    @JsonValue
    public String toString() {
        return this.medium;
    }

    public static TransactionMedium fromString(String mediumString) {
        for (TransactionMedium medium : TransactionMedium.values()) {
            if (medium.toString().equalsIgnoreCase(mediumString)) {
                return medium;
            }
        }
        return null;
    }

}
