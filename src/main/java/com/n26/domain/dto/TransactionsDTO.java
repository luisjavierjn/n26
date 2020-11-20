package com.n26.domain.dto;

import java.io.Serializable;

public class TransactionsDTO implements Serializable {

    private static final long serialVersionUID = 800402947669081889L;

    private String amount;

    private String timestamp;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
