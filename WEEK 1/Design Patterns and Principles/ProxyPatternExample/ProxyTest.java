public class ProxyTest {

    public static void main(String[] args) {

        Image image = new ProxyImage("sample.jpg");

        System.out.println("First Display:");
        image.display();

        System.out.println("\nSecond Display:");
        image.display();
    }
}
