public class PaymentAdapter implements PaymentProcessor {
    private Object gateway;

    public PaymentAdapter(Object gateway) {
        this.gateway = gateway;
    }

    @Override
    public void processPayment(double amount) {
        if (gateway instanceof PayPalGateway) {
            ((PayPalGateway) gateway).sendMoney(amount);
        } else if (gateway instanceof StripeGateway) {
            ((StripeGateway) gateway).executeCharge(amount);
        }
    }
}