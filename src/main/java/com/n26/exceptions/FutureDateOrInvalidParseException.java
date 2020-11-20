package com.n26.exceptions;

public class FutureDateOrInvalidParseException extends Exception {

    public FutureDateOrInvalidParseException(String aMessage) {
        super(aMessage);
    }
}
