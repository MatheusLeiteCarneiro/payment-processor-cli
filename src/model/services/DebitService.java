package model.services;

public class DebitService implements PaymentService{
    private static final double DEBIT_FEE = 0.0125;
    @Override
    public double paymentProcessing(double value, int installments) {
        return value + value * DEBIT_FEE;
    }
}
