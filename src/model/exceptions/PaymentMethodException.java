package model.exceptions;

public class PaymentMethodException extends RuntimeException {
    public PaymentMethodException(String message) {
        super(message);
    }
}
