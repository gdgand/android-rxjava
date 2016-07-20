package com.androidhuman.example.rxjavainaction.error;

public final class InsufficientBalanceError extends Error {

    private int amount;

    public InsufficientBalanceError(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
