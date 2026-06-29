public class AdapterTest {
    public static void main(String[] args) {
        PaymentProcessor pp = new PaymentAdapter(new PayPalGateway());
        pp.processPayment(100.0);

        PaymentProcessor sg = new PaymentAdapter(new StripeGateway());
        sg.processPayment(250.0);
    }
}