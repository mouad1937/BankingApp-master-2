package com.codecrackers.bankapi.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionStatus {

    PENDING("Pending"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");


    private final String status;


    private TransactionStatus(String status) {
        this.status = status;

    }


    @Override
    @JsonValue
    public String toString() {
        return this.status;
    }

    public static TransactionStatus fromString(String statusString) {
        for (TransactionStatus status : TransactionStatus.values()) {
            if (status.toString().equalsIgnoreCase(statusString)) {
                return status;
            }
        }
        return null;
    }


}
