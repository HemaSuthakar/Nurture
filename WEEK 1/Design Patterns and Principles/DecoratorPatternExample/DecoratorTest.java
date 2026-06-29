public class DecoratorTest {

    public static void main(String[] args) {

        System.out.println("Email Notification:");
        Notifier emailNotifier = new EmailNotifier();
        emailNotifier.send("Meeting at 10 AM");

        System.out.println();

        System.out.println("Email + SMS Notification:");
        Notifier smsNotifier =
                new SMSNotifierDecorator(new EmailNotifier());
        smsNotifier.send("Meeting at 10 AM");

        System.out.println();

        System.out.println("Email + SMS + Slack Notification:");
        Notifier multiNotifier =
                new SlackNotifierDecorator(
                        new SMSNotifierDecorator(
                                new EmailNotifier()));

        multiNotifier.send("Meeting at 10 AM");
    }
}
