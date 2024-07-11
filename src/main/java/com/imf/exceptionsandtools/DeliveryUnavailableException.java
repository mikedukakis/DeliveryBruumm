package com.imf.exceptionsandtools;

public class DeliveryUnavailableException extends Exception {
    public DeliveryUnavailableException() {}
    public DeliveryUnavailableException(String msg) {
        super(msg);
    }
}
